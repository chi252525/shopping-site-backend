package com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.wholesaler;

import com.shopping.shopping_site_backend.shoppingSite.front.api.intranet.wholesaler.WholesalerPresentation;
import com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.wholesaler.model.WholesalerRequest;
import com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.wholesaler.model.WholesalerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Wholesaler")
@RequiredArgsConstructor
@RestController
@RequestMapping("${intranet}/wholesaler")
public class WholesalerController {

  private final WholesalerPresentation wholesalerPresentation;

  // Create Wholesaler
  @Operation(
      tags = {"Wholesaler"},
      summary = "Create Wholesaler",
      description = "Create a new wholesaler")
  @PostMapping("/create")
  public ResponseEntity<WholesalerResponse> createWholesaler(
      @Parameter(required = true) @Valid @RequestBody WholesalerRequest request) {
    WholesalerResponse wholesaler = wholesalerPresentation.createWholesaler(request);
    return new ResponseEntity<>(wholesaler, HttpStatus.CREATED);
  }

  // Get Wholesaler by ID
  @Operation(
      tags = {"Wholesaler"},
      summary = "Get Wholesaler by ID",
      description = "Get wholesaler details by ID")
  @GetMapping("/{id}/get")
  public ResponseEntity<WholesalerResponse> getWholesalerById(
      @Parameter(required = true) @PathVariable("id") Long id) {
    WholesalerResponse wholesaler = wholesalerPresentation.getWholesalerById(id);
    return wholesaler != null
        ? new ResponseEntity<>(wholesaler, HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  // Get All Wholesalers
  @Operation(
      tags = {"Wholesaler"},
      summary = "Get All Wholesalers")
  @GetMapping("/list")
  public ResponseEntity<List<WholesalerResponse>> getAllWholesalers() {
    List<WholesalerResponse> wholesalers = wholesalerPresentation.query();
    return new ResponseEntity<>(wholesalers, HttpStatus.OK);
  }

  // Update Wholesaler
  @Operation(
      tags = {"Wholesaler"},
      summary = "Update Wholesaler",
      description = "Update an existing wholesaler by ID")
  @PutMapping("/{id}/update")
  public ResponseEntity<WholesalerResponse> updateWholesaler(
      @Parameter(required = true) @PathVariable("id") Long id,
      @Parameter(required = true) @Valid @RequestBody WholesalerRequest request) {
    wholesalerPresentation.updateWholesaler(id, request);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // Delete Wholesaler
  @Operation(
      tags = {"Wholesaler"},
      summary = "Delete Wholesaler",
      description = "Delete a wholesaler by ID")
  @DeleteMapping("/{id}/delete")
  public ResponseEntity<Void> deleteWholesaler(
      @Parameter(required = true) @PathVariable("id") Long id) {
    wholesalerPresentation.deleteWholesaler(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
