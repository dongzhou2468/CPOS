/**
 * Copyright (c) 2019, TP-Link Co.,Ltd.
 * Author: chenhang <chenhang@tp-link.com.cn>
 * Created: 2019-06-17
 */
package chen.pos.welcome.dao;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import chen.pos.welcome.bean.Product;

public class DaoProxy {

    private static final DaoProxy DAO_PROXY = new DaoProxy();

    private static SqlSession sqlSession = null;

    private static final String GET_PRODUCT_BY_PRODUCT_CODE = "chen.pos.welcome.mapper.ProductMapper.getProductByProductCode";
    private static final String GET_PRODUCT_BY_BAR_CODE = "chen.pos.welcome.mapper.ProductMapper.getProductByBarCode";

    private DaoProxy() {}

    public static void init() {
        String resource = "mybatis-configuration.xml";
        InputStream is = DAO_PROXY.getClass().getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        sqlSession = factory.openSession();
    }

    public static DaoProxy getInstance() {
        return DAO_PROXY;
    }

    public Product query(String productCode) {
        String method = (productCode.length() == 8) ? GET_PRODUCT_BY_PRODUCT_CODE : GET_PRODUCT_BY_BAR_CODE;
        Product product = sqlSession.selectOne(method, productCode);
        System.out.println(product);
        return product;
    }

    public void close() {
        sqlSession.close();
    }
}
