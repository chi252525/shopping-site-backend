CREATE TABLE ec_order (
    id SERIAL PRIMARY KEY,  -- 自动递增的 ID
    indicationOrderNumber VARCHAR(255) NOT NULL,  -- 订单编号，字符串类型
    orderDate TIMESTAMP NOT NULL,  -- 订单日期，日期时间类型
    paymentDate TIMESTAMP,  -- 支付日期，日期时间类型
    paymentType VARCHAR(100),  -- 支付类型，字符串类型
    status VARCHAR(50) DEFAULT 'Shipping Complete',  -- 状态，字符串类型，默认值为 'Shipping Complete'
    shopperType VARCHAR(50) DEFAULT 'Shoppee',  -- 购物者类型，字符串类型，默认值为 'Shoppee'
    shopperName VARCHAR(255) NOT NULL,  -- 购物者姓名，字符串类型
    shopperPaidAmount DOUBLE PRECISION NOT NULL,  -- 购物者支付金额，双精度浮点数
    discountTotal DOUBLE PRECISION DEFAULT 0.0,  -- 折扣总额，双精度浮点数，默认值为 0.0
    shopperId INT NOT NULL  -- 购物者 ID，整型
);

CREATE TABLE ec_product (
    id SERIAL PRIMARY KEY,                    -- 产品 ID，自增主键
    base_sku VARCHAR(100) NOT NULL,           -- 基本 SKU
    name VARCHAR(255) NOT NULL,                -- 产品名称
    unit_price DECIMAL(10, 2) NOT NULL,       -- 单位价格，包含两位小数
    sale_price DECIMAL(10, 2),                 -- 销售价格，包含两位小数
    discount_price DECIMAL(10, 2),             -- 折扣价格，包含两位小数
    version_id INT NOT NULL,                   -- 版本 ID
    unit VARCHAR(50) NOT NULL,                 -- 单位
    in_stock BOOLEAN NOT NULL,                 -- 是否有库存
    available_end_time TIMESTAMP,              -- 可用结束时间
    available_start_time TIMESTAMP,            -- 可用开始时间
    merchant_id INT NOT NULL,                   -- 商家 ID

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 创建时间
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  -- 更新时间
);
