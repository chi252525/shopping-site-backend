# RECOMMENDATION

尺寸對應

| 項次 | 欄位名稱                   | 資料型別     | 長度 | 預設值 | 允許空值 | 欄位備註                                          |
|----|------------------------|----------|----|-----|------|-----------------------------------------------|
| 1  | id                     | LONG     |    |     | NO   | 資料鍵值，PrimaryKey                               |
| 2  | age_start                    | INTEGER   |    |     | NO   | 最小年齡                                          |
| 3  | age_end                   | INTEGER   |    |     | NO   | 最大年齡                                          |
| 4  | height_start              | INTEGER  |    |     | NO   |  最小身高                                           |
| 5  | height_end             | INTEGER  |    |     | NO   |  |  最大身高
| 6  | template_id             | LONG  |    |     | NO   |  |  模板 Id  FK [TEMPLATE](/src/main/doc/DB/TEMPLATE)
| 7  | product_type | STRING  |    |     | NO   | 商品類型                                                                                       |

```sql
SELECT p.product_name, a.detail
FROM product p
JOIN attribute a ON p.product_id = a.product_id
WHERE
    -- 匹配年龄范围
    int4range(lower(a.detail->>'age_range'), upper(a.detail->>'age_range')) @> 11
    AND
    -- 匹配身高范围
    int4range(lower(a.detail->>'height_range'), upper(a.detail->>'height_range')) @> 153
```