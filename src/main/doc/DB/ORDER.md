# ORDER

訂單

| 項次 | 欄位名稱                    | 資料型別   | 長度 | 預設值 | 允許空值 | 欄位備註            |
|----|-------------------------|--------|----|-----|------|-----------------|
| 1  | id                      | LONG   |    |     | NO   | 資料鍵值，PrimaryKey |
| 2  | indication_order_number | STRING |    |     | NO   | 訂單編號            |
| 3  | order_date              | DATE   |    |     | NO   | 訂單日期            |
| 4  | payment_date            | DATE |    |     | NO   | 付款日期            |
| 5  | shipping_type           | DATE   |    |     | NO   | 運送方式            |
| 6  | shipping_status         | DATE   |    |     | NO   | 運送狀態            |
| 6  | payment_type            | DATE   |    |     | NO   | 付款方式            |
| 7  | payment_status          | DATE   |    |     | NO   | 付款狀態            |
| 8  | shopper_type            | DATE   |    |     | NO   | 購買者類型           |
| 9  | shopper_name            | DATE   |    |     | NO   | 購買者名稱           |
| 10 | shopper_paid_amount     | DATE   |    |     | NO   | 購買者付款金額         |
| 11 | discount_total          | DATE   |    |     | YES  | 折扣總額            |
| 12 | shipping_fee            | DATE   |    |     | NO   | 運費              |
| 13 | shopper_phone           | DATE   |    |     | NO   | 購買者電話           |
| 14 | shopper_email           | DATE   |    |     | NO   | 購買者電子郵件         |
| 15 | receiver_address        | DATE   |    |     | YES  | 收件者地址           |
| 16 | receiver_name           | DATE   |    |     | NO   | 收件者名稱           |
| 17 | receiver_phone          | DATE   |    |     | NO   | 收件者電話           |
| 18 | receiver_shop_id        | DATE   |    |     | YES  | 收件商店編號          |
| 19 | receiver_shop_name      | DATE   |    |     | YES  | 收件商店名稱          |
| 20 | receiver_shop_address   | DATE   |    |     | YES  | 收件商店地址          |
| 21 | shopper_id              | DATE   |    |     | NO   | 購買者  FK         |