package org.vividus.ui.web.action.search;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.How;
import org.vividus.ui.action.search.IElementSearchAction;
import org.vividus.ui.action.search.LocatorType;
import org.vividus.ui.action.search.SearchParameters;

public class ShadowCssSelectorSearch extends AbstractWebElementSearchAction implements IElementSearchAction
{
    public ShadowCssSelectorSearch(LocatorType type)
    {
        super(WebLocatorType.SHADOW_CSS_SELECTOR);
    }

    @Override
    public List<WebElement> search(SearchContext searchContext, SearchParameters parameters)
    {
        if (parameters.getShadowHost() == null || parameters.getValue() == null)
        {
            throw new IllegalArgumentException("Element in shadow mode can not be found without locator "
                    + "to host and itself");
        }

//        List<WebElement> hosts = findElements(searchContext, getHow().buildBy(parameters.getShadowHost()), parameters);

        List<WebElement> targetElements = new ArrayList<>();
//        for (WebElement host : hosts)
//        {
//            targetElements.addAll(searchElementsInsideShadowTree(host, parameters.getValue()));
//        }
        return targetElements;
    }

    private List<WebElement> searchElementsInsideShadowTree(WebElement host, String target)
    {
        if (isShadowRootAvailable(host))
        {
            return (List<WebElement>) getJavascriptActions()
                     .executeScript("return arguments[0].shadowRoot.querySelector(arguments[1])", host, target);
        }
        else
        {
            throw new NoSuchElementException("The element is not a shadow host: " + host);
        }
    }

    private boolean isShadowRootAvailable(WebElement host)
    {
        return getJavascriptActions().executeScript("return arguments[0].shadowRoot", host) != null;
    }
}
