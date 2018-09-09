package com.aapps.wikisearch.database;

import com.aapps.wikisearch.database.models.RealmPage;
import com.aapps.wikisearch.database.models.RealmTerms;
import com.aapps.wikisearch.database.models.RealmThumbnail;
import com.aapps.wikisearch.search.model.Pages;

import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class RealmSearchController {

    Realm realm;

    public RealmSearchController() {
        realm = Realm.getDefaultInstance();
    }

    public RealmResults<RealmPage> getSavedSearch() {
        return realm.where(RealmPage.class).findAllSorted("updatedDate", Sort.DESCENDING);
    }

    public void saveSearchResult(final Pages pages) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                /**
                 * Check if the database already has 10 records. If yes then remove the old record
                 */
                long count = realm.where(RealmPage.class).count();
                if (count == 10) {
                    realm.where(RealmPage.class).findAllSorted("updatedDate", Sort.ASCENDING).first().deleteFromRealm();
                }

                /**
                 * Add new item to search
                 */
                RealmPage realmPage = new RealmPage();
                realmPage.setIndex(pages.getIndex());
                realmPage.setNs(pages.getNs());
                realmPage.setTitle(pages.getTitle());
                realmPage.setPageid(pages.getPageid());

                RealmTerms realmTerms = new RealmTerms();
                if (pages.getTerms() != null) {
                    realmTerms.setDescription(pages.getTerms().getDescription().get(0));
                }

                RealmThumbnail realmThumbnail = new RealmThumbnail();
                if (pages.getThumbnail() != null) {
                    realmThumbnail.setHeight(pages.getThumbnail().getHeight());
                    realmThumbnail.setWidth(pages.getThumbnail().getWidth());
                    realmThumbnail.setSource(pages.getThumbnail().getSource());
                }
                realmPage.setThumbnail(realmThumbnail);
                realmPage.setTerms(realmTerms);

                Calendar c = Calendar.getInstance();
                Date currentDate = new Date(c.getTimeInMillis());
                realmPage.setUpdatedDate(currentDate);
                realm.insertOrUpdate(realmPage);
            }
        });
    }
}
