package kr.ac.hansung.cse.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.cse.model.Product;

@Repository
public class ProductDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Product> getProducts() { // record 형태를 객체로 디파인 해준

		String sqlStatemenet = "select * from product";
		return jdbcTemplate.query(sqlStatemenet, new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				Product product = new Product();

				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setCategory(rs.getString("category"));
				product.setManufacturer(rs.getString("manufacturer"));
				product.setUnitInStock(rs.getInt("unitInStock"));
				product.setDescription(rs.getString("description"));

				return product;

			}

		});

	}

	public boolean addProduct(Product product) {
		String name = product.getName();
		String category = product.getCategory();
		int price = product.getPrice();
		String manufacturer = product.getManufacturer();
		int unitInStock = product.getUnitInStock();
		String description = product.getDescription();

		String sqlStatement = "insert into product (name, category, price, manufacturer, unitInStock, description ) "
				+ "values (?,?,?,?,?,?)";

		return (jdbcTemplate.update(sqlStatement,
				new Object[] { name, category, price, manufacturer, unitInStock, description }) == 1);

	}

	public boolean deleteProduct(int id) {

		String sqlStatement = "delete from product where id=?";

		return (jdbcTemplate.update(sqlStatement, new Object[] { id }) == 1);
	}

	public Product getProductById(int id) {

		String sqlStatemenet = "select *from product where id=?";

		return jdbcTemplate.queryForObject(sqlStatemenet, new Object[] { id }, new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				Product product = new Product();

				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setCategory(rs.getString("category"));
				product.setManufacturer(rs.getString("manufacturer"));
				product.setUnitInStock(rs.getInt("unitInStock"));
				product.setDescription(rs.getString("description"));

				return product;

			}

		});

	}

	public boolean updateProduct(Product product) {

		int id = product.getId();
		String name = product.getName();
		String category = product.getCategory();
		int price = product.getPrice();
		String manufacturer = product.getManufacturer();
		int unitInStock = product.getUnitInStock();
		String description = product.getDescription();

		String sqlStatement = "update product set name=?, category=?, price=?, manufacturer=?, unitInStock=?, description=? "
				+ "where id=?";

		return (jdbcTemplate.update(sqlStatement,
				new Object[] { name, category, price, manufacturer, unitInStock, description, id }) == 1);

	}

}