# PRODUCT

商品

| 項次 | 欄位名稱                     | 資料型別     | 長度 | 預設值 | 允許空值 | 欄位備註                                |
|----|--------------------------|----------|----|-----|------|-------------------------------------|
| 1  | id                       | LONG     |    |     | NO   | 資料鍵值，PrimaryKey                     |
| 2  | base_sku                 | STRING   |    |     | NO   | 商品編號                                |
| 3  | name                     | STRING   |    |     | NO   | 商品名稱                                |
| 4  | unit_price               | INTEGER  |    |     | NO   | 商品成本                                |
| 5  | discount_price           | INTEGER  |    |     | NO   | 商品優惠價                               |
| 6  | sale_price               | INTEGER  |    |     | NO   | 商品販售價                               |
| 7  | version_id               | STRING   |    |     | NO   | 版本編號 UUID                           |
| 8  | unit                     | STRING   |    |     | NO   | 單位                                  |
| 9  | in_stock                 | BOOLEAN  |    |     | NO   | 是否已到貨                               |
| 10 | available_start_time     | DATETIME |    |     | NO   | 上架時間                                |
| 11 | available_end_time       | DATETIME |    |     | NO   | 下架時間                                |
| 12 | is_show                  | BOOLEAN  |    |     | NO   | 是否顯示在前台                             |
| 13 | first_category_id        | LONG     |    |     | NO   | 第一層分類  FK [CATEGORY.md](CATEGORY.md) |
| 14 | second_category_id       | LONG     |    |     | NO   | 第二層分類  FK [CATEGORY.md](CATEGORY.md) |
| 15 | is_settled               | BOOLEAN  |    |     | NO   | 是否已结清                               |
| 16 | is_old                   | BOOLEAN  |    |     | NO   | 是否為舊貨                               |
| 17 | estimated_total_profit   | INTEGER  |    |     | NO   | 預估總利潤                               |
| 18 | wholesaler_id            | INTEGER  |    |     | NO   | 廠商ID FK  [WHOLESALER.md](WHOLESALER.md)  |
| 19 | description              | STRING   |    |     | NO   | 商品描述                                |
| 20 | return_quantity_on_cancel | BOOLEAN  |    |     | NO   | 是否要庫存返還                             |
| 21 | alert_threshold          | INTEGER  |    |     | NO   | 庫存警示數                               |
| 22 | total_cost               | INTEGER  |    |     | NO   | 商品總成本                               |
| 23 | estimated_profit         | INTEGER  |    |     | NO   | 單件利潤                                |
| 24 | third_category_id        | LONG     |    |     | NO   | 第三層分類  FK [CATEGORY.md](CATEGORY.md) |
