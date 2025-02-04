# ATTRIBUTE

商品

| 項次 | 欄位名稱         | 資料型別    | 長度 | 預設值 | 允許空值 | 欄位備註                                                                                       |
|----|--------------|---------|----|-----|------|--------------------------------------------------------------------------------------------|
| 1  | id           | LONG    |    |     | NO   | 資料鍵值，PrimaryKey                                                                            |
| 2  | sku          | STRING  |    |     | NO   | 商品編號                                                                                       |
| 3  | name         | STRING  |    |     | NO   | 商品名稱                                                                                       |
| 4  | inventory    | INTEGER |    |     | NO   | 庫存                                                                                         |
| 5  | product_id   | INTEGER |    |     | NO   | 商品 ID FK [PRODUCT](/src/main/doc/DB/PRODUCT)                                               |
| 6  | product_type | STRING  |    |     | NO   | 商品類型                                                                                       |
| 7  | detail       | JSONB   |    |     | NO   | {"age_range": "10-12", "height_range": "150-155", "chest_width": "40cm", "length": "60cm"} |
