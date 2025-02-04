package com.shopping.shopping_site_backend.shoppingSite.front.api.intranet.wholesaler;

import com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.wholesaler.model.WholesalerRequest;
import com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.wholesaler.model.WholesalerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WholesalerPresentation {

  private final WholesalerFlow wholesalerFlow;

  public WholesalerResponse createWholesaler(WholesalerRequest request) {
    return wholesalerFlow.createWholesaler(request);
  }

  public WholesalerResponse getWholesalerById(Long id) {
    return wholesalerFlow.getWholesalerById(id);
  }

  public List<WholesalerResponse> query() {
    return wholesalerFlow.query();
  }

  public void updateWholesaler(Long id, WholesalerRequest request) {
    wholesalerFlow.updateWholesaler(id, request);
  }

  public void deleteWholesaler(Long id) {
    wholesalerFlow.deleteWholesaler(id);
  }
}