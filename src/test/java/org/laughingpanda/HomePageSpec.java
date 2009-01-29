package org.laughingpanda;

import jdave.junit4.JDaveRunner;
import jdave.wicket.ComponentSpecification;

import org.apache.wicket.model.IModel;
import org.junit.runner.RunWith;

/**
 * @author Markus Hjort / Reaktor Innovations Oy
 */
@RunWith(JDaveRunner.class)
public class HomePageSpec extends ComponentSpecification<HomePage> {
	public class Any {
		public HomePage create() {
			return startComponent();
		}
		
		public void rendersPage() {
		}
	}
	@Override
	protected HomePage newComponent(String id, IModel model) {
		return new HomePage();
	}
}
