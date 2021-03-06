/*
 * Copyright 2019-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.vividus.ui.web.action.search;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.vividus.ui.action.search.SearchParameters;
import org.vividus.ui.action.search.Visibility;

class CaseInsensitiveTestSearchTests
{
    private final CaseInsensitiveTextSearch search = new CaseInsensitiveTextSearch();

    @Test
    void testSearch()
    {
        List<WebElement> webElements = List.of(mock(WebElement.class));
        SearchContext searchContext = mock(SearchContext.class);
        SearchParameters parameters = new SearchParameters("locator", Visibility.ALL, false);

        when(searchContext.findElements(By.xpath(
                ".//*[text()[normalize-space(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'))"
                + "=\"locator\"] or @*[normalize-space(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', "
                + "'abcdefghijklmnopqrstuvwxyz'))=\"locator\"] or *[normalize-space(translate(., "
                + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'))=\"locator\"] and not(.//*[text()"
                + "[normalize-space(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'))="
                + "\"locator\"] or @*[normalize-space(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', "
                + "'abcdefghijklmnopqrstuvwxyz'))=\"locator\"] or *[normalize-space(translate(.,"
                + " 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'))=\"locator\"]])]")))
            .thenReturn(webElements);

        assertEquals(webElements, search.search(searchContext, parameters));
    }

    @Test
    void testAttributeKey()
    {
        assertEquals(WebLocatorType.CASE_INSENSITIVE_TEXT, search.getType());
    }
}
