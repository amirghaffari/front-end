package frontend.service;

import frontend.model.ConditionMaker;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import frontend.model.Search;
import frontend.model.Publication;
import frontend.model.comparator.PublicationRateComparator;
import frontend.service.interfaces.PublicationServiceInterface;
import frontend.service.interfaces.SearchServiceInterface;
import frontend.utils.Util;
import java.util.Collections;
import javax.servlet.http.HttpSession;

/**
*
* @author Amir Ghaffari
*/

@Service
public class SearchService implements SearchServiceInterface {

    @Autowired
    PublicationServiceInterface publicationService;

    @Override
    public List<Publication> getSearchResult(Search search, HttpSession session) {
        List<ConditionMaker> connectionList= search.getCondition();
        List<Publication> publicationList= publicationService.fetchPublicationWithDetails( connectionList,session);
        if(!Util.isLocalData(session))return publicationList;
        List<Publication> pubList= SampleData.getLocalData(session).applySearch(search);
        Collections.sort(publicationList, new PublicationRateComparator());
        return pubList;
    }

}
