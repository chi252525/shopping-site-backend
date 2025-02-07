# ORDERITEM

訂單項目

| 項次 | 欄位名稱                | 資料型別   | 長度 | 預設值 | 允許空值 | 欄位備註                               |
|----|---------------------|--------|----|-----|------|------------------------------------|
| 1  | id                  | LONG   |    |     | NO   | 資料鍵值，PrimaryKey                    |
| 2  | order_id            | LONG   |    |     | NO   | 訂單 ID FK  [ORDER.md](ORDER.md)     |
| 3  | product_id          | LONG   |    |     | NO   | 商品 ID FK  [PRODUCT.md](PRODUCT.md) |
| 4  | sku                 | STRING |    |     | NO   | 商品編號                               |
| 5  | product_name        | STRING |    |     | NO   | 商品名稱                               |
| 6  | product_price       | LONG   |    |     | NO   | 商品價格                               |
| 7  | shopper_paid_amount | LONG   |    |     | NO   | 購買者付款金額                            |
| 8  | discount_amount     | LONG   |    |     | YES  | 折扣金額                               |
| 9  | quantity            | LONG   |    |     | NO   | 數量                                 |
| 10 | total_amount        | LONG   |    |     | NO   | 總金額                                |
| 11 | version_id          | STRING |    |     | NO   | 版本編號 UUID                             |