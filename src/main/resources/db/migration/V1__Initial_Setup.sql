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