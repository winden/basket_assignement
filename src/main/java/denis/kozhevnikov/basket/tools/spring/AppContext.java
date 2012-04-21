package denis.kozhevnikov.basket.tools.spring;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Class-single point of boiler-plate code to use spring context.
 */
public class AppContext {
	private static final String APP_CONFIG_XML = "spring/app-context.xml";
	private static final XmlBeanFactory BEAN_FACTORY = new XmlBeanFactory(new ClassPathResource(APP_CONFIG_XML));

	/**
	 * Method to access beans defined in application spring context.
	 * @param aBeanId bean id within the context.
	 * @return the bean by aBeanId. If such bean doesn't exist throws BeansException.
	 * @throws BeansException
	 */
	public static Object getBean(final String aBeanId) {
		return BEAN_FACTORY.getBean(aBeanId);
	}
}