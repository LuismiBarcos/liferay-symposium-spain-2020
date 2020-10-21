package com.liferay.travel.internal.search.spi.model.query.contributor;

import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.search.query.QueryHelper;
import com.liferay.portal.search.spi.model.query.contributor.KeywordQueryContributor;
import com.liferay.portal.search.spi.model.query.contributor.helper.KeywordQueryContributorHelper;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Luis Miguel Barcos
 */
@Component(
        immediate = true,
        property = "indexer.class.name=com.liferay.travel.model.Trip",
        service = KeywordQueryContributor.class
)
public class TripKeywordQueryContributor implements KeywordQueryContributor{
    @Reference
    protected QueryHelper queryHelper;
    @Override
    public void contribute(String keywords, BooleanQuery booleanQuery, KeywordQueryContributorHelper keywordQueryContributorHelper) {
        SearchContext searchContext = keywordQueryContributorHelper.getSearchContext();
        queryHelper.addSearchTerm(booleanQuery, searchContext, Field.TITLE, true);
        queryHelper.addSearchTerm(booleanQuery, searchContext, Field.DESCRIPTION, true);
    }
}
