package com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.product;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "產品同步")
@RequiredArgsConstructor
@RestController
@RequestMapping("${intranet}/shopee/sync")
public class SyncShopeeController {}
