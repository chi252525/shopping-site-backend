package com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.order;

import com.shopping.shopping_site_backend.shoppingSite.front.api.intranet.order.OrderTransactionPresentation;
import com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.order.model.OrderTransactionResponseV1_0;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "下單")
@RequiredArgsConstructor
@RestController
@RequestMapping("${intranet}/order/v1.0")
public class OrderController {
    private final OrderTransactionPresentation orderTransactionPresentation;

    @Operation(
            tags = {"下單"},
            summary = "下單v1.0",
            description = "下單")
    @PostMapping("/orderTransaction")
    public OrderTransactionResponseV1_0 orderTransaction(
            @Parameter(required = true) @Valid @RequestBody OrderTransactionResponseV1_0 request)
            {
        return this.orderTransactionPresentation.presentV1_0(request);
    }
}