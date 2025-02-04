package com.shopping.shopping_site_backend.shoppingSite.front.api.intranet.wholesaler;

import com.shopping.shopping_site_backend.infra.dataprovider.entity.wholesaler.Wholesaler;
import com.shopping.shopping_site_backend.infra.sys.spring.repository.WholesalerRepository;
import com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.wholesaler.model.WholesalerRequest;
import com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.wholesaler.model.WholesalerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WholesalerFlow {

  private final WholesalerRepository wholesalerRepository;

  public WholesalerResponse createWholesaler(WholesalerRequest request) {
    Wholesaler wholesaler = toWholesaler(request);
    wholesaler = wholesalerRepository.save(wholesaler);
    return toWholesalerResponse(wholesaler);
  }

  public WholesalerResponse getWholesalerById(Long id) {
    Optional<Wholesaler> wholesalerOptional = wholesalerRepository.findById(id);
    if (!wholesalerOptional.isPresent()) {
      // TODO throw exception 404
      throw new RuntimeException();
    }
    return toWholesalerResponse(wholesalerOptional.get());
  }

  public List<WholesalerResponse> query() {
    List<Wholesaler> wholesalers = wholesalerRepository.findAll();
    return wholesalers.stream()
        .map(this::toWholesalerResponse)
        .collect(Collectors.toList());
  }

  public void updateWholesaler(Long id, WholesalerRequest request) {
    Optional<Wholesaler> wholesalerOptional = wholesalerRepository.findById(id);
    if (!wholesalerOptional.isPresent()) {
      throw new RuntimeException();
    }
    Wholesaler wholesaler = wholesalerOptional.get();
    updateWholesalerFromRequest(wholesaler, request);
    wholesalerRepository.save(wholesaler);
  }

  public void deleteWholesaler(Long id) {
    Optional<Wholesaler> wholesalerOptional = wholesalerRepository.findById(id);
    if (!wholesalerOptional.isPresent()) {
      throw new RuntimeException();
    }
    wholesalerRepository.delete(wholesalerOptional.get());
  }

  private Wholesaler toWholesaler(WholesalerRequest request) {
    Wholesaler wholesaler = new Wholesaler();
    updateWholesalerFromRequest(wholesaler, request);
    return wholesaler;
  }

  private void updateWholesalerFromRequest(Wholesaler wholesaler, WholesalerRequest request) {
    BeanUtils.copyProperties(request, wholesaler);
  }

  private WholesalerResponse toWholesalerResponse(Wholesaler wholesaler) {
    WholesalerResponse response = new WholesalerResponse();
    BeanUtils.copyProperties(wholesaler, response);
    return response;
  }
}

