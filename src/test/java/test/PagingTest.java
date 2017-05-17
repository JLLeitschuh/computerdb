package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ui.Page;

public class PagingTest {

	/**
	 * test page .
	 */
	@Test
	public void paginationTest() {

		Page page = new Page();
		page.setNumberItemPage(20);
		page.setNumberOfTotalItem(1000);

		assertEquals(page.getNumberPage(), 50);
		assertEquals(page.startPage(), 1);

		page.setCurrentPage(10);

		assertEquals(page.startPage(), 5);
		assertEquals(page.endPage(), 10);

		page.setCurrentPage(page.endPage());
		assertEquals(page.startPage(), 6);
		assertEquals(page.endPage(), 11);

		page.setCurrentPage(page.startPage());
		assertEquals(page.startPage(), 5);
		assertEquals(page.endPage(), 10);

		page.setCurrentPage(100);

		assertEquals(page.startPage(), 44);
		assertEquals(page.endPage(), 49);

		page.setCurrentPage(-1);

		assertEquals(page.startPage(), 1);
		assertEquals(page.endPage(), 6);

	}

}
