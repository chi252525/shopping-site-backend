package com.shopping.shopping_site_backend.infra.dataprovider.entity.common;

public interface ErrorInfo {
  /**
   * get ErrorMessage
   *
   * @return errorMessage
   */
  String getErrorMessage();

  /**
   * get Error Code
   *
   * @return Error Code
   */
  String getErrorCode();
}
