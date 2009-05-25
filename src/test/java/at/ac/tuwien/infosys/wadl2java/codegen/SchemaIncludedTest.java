package at.ac.tuwien.infosys.wadl2java.codegen;

import java.util.List;

import org.junit.Test;

import at.ac.tuwien.infosys.Assert;
import at.ac.tuwien.infosys.BaseTest;
import at.ac.tuwien.infosys.wadl2java.JavaSource;
import at.ac.tuwien.infosys.wadl2java.Wadl2Java;

public class SchemaIncludedTest extends BaseTest {

	@Test
	public void testParseSchemaIncludingWadl() throws Exception {
		String xml = loadResource("at.ac.tuwien.infosys.wadl2java.codegen.SchemaIncludedTest.txt");
		String java = loadResource("at.ac.tuwien.infosys.wadl2java.codegen.SchemaIncludedTest.iava");
		String customer = loadResource("at.ac.tuwien.infosys.wadl2java.codegen.SchemaIncludedTestCustomer.iava");
		String customers = loadResource("at.ac.tuwien.infosys.wadl2java.codegen.SchemaIncludedTestCustomers.iava");
		String product = loadResource("at.ac.tuwien.infosys.wadl2java.codegen.SchemaIncludedTestProduct.iava");

		List<JavaSource> sources = new Wadl2Java().generate(xml, "test");

		Assert.assertStringEqualsIgnoreCarriageReturns(customer, getJavaSource("Customer", sources).toString());
		Assert.assertStringEqualsIgnoreCarriageReturns(customers, getJavaSource("Customers", sources).toString());
		Assert.assertStringEqualsIgnoreCarriageReturns(product, getJavaSource("Product", sources).toString());
		Assert.assertStringEqualsIgnoreCarriageReturns(java, getJavaSource("CustomerService", sources).toString());
	}

	@Test
	public void testParseItemsResource() throws Exception {
		String xml = loadResource("at.ac.tuwien.infosys.wadl2java.codegen.ItemsResource.txt");
		String java = loadResource("at.ac.tuwien.infosys.wadl2java.codegen.ItemsResource.iava");
		String item = loadResource("at.ac.tuwien.infosys.wadl2java.codegen.ItemsResourceItem.iava");

		List<JavaSource> sources = new Wadl2Java().generate(xml, "test");
		Assert.assertStringEqualsIgnoreCarriageReturns(java, getJavaSource("ItemsResource", sources).toString());
		Assert.assertStringEqualsIgnoreCarriageReturns(item, getJavaSource("Item", sources).toString());
	}
}
