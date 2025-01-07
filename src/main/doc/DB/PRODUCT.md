# PRODUCT

商品

| 項次 | 欄位名稱                 | 資料型別     | 長度 | 預設值 | 允許空值 | 欄位備註                                            |
|----|----------------------|----------|----|-----|------|-------------------------------------------------|
| 1  | id                   | LONG     |    |     | NO   | 資料鍵值，PrimaryKey                                 |
| 2  | base_sku             | STRING   |    |     | NO   | 商品編號                                            |
| 3  | name                 | STRING   |    |     | NO   | 商品名稱                                            |
| 4  | unit_price           | INTEGER  |    |     | NO   | 商品成本                                            |
| 5  | discount_price       | INTEGER  |    |     | NO   | 商品優惠價                                           |
| 6  | sale_price           | INTEGER  |    |     | NO   | 商品販售價                                           |
| 7  | version_id           | STRING   |    |     | NO   | 版本編號 UUID                                       |
| 8  | unit                 | STRING   |    |     | YES  | 單位                                              |
| 9  | in_stock             | BOOLEAN  |    |     | YES  | 是否已到貨                                           |
| 10 | available_start_time | DATETIME |    |     | YES  | 上架時間                                            |
| 11 | available_end_time   | DATETIME |    |     | YES  | 下架時間                                            |
| 12 | is_show              | BOOLEAN  |    |     | YES  | 是否顯示在前台                                         |
| 13 | first_category_id    | LONG     |    |     | YES  | 第一層分類  FK [CATEGORY](/src/main/doc/DB/CATEGORY) |
| 14 | second_category_id   | LONG     |    |     | YES  | 第二層分類  FK [CATEGORY](/src/main/doc/DB/CATEGORY)                                      |
| 15 | is_settled           | BOOLEAN  |    |     | YES  | 是否已结清                                           |