package olutopas.dao;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.Transaction;
import com.avaje.ebean.config.DataSourceConfig;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.config.dbplatform.SQLitePlatform;
import java.util.List;
import olutopas.Main;
import olutopas.dao.Datamapper;
import olutopas.domain.Beer;
import olutopas.domain.Brewery;
import olutopas.domain.Rating;
import olutopas.domain.User;

public class EbeanSqliteDatamapper implements Datamapper {

    private Class[] luokat;
    private EbeanServer server;
    private String tietokantaUrl;
    private boolean dropAndCreate;

    private User user;

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }
    
    public EbeanSqliteDatamapper(String tietokantaUrl, boolean dropAndCreate, Class... luokat) {
        this.luokat = luokat;
        this.dropAndCreate = dropAndCreate;
        this.tietokantaUrl = tietokantaUrl;
        init();
    }

    public void init() {
        ServerConfig config = new ServerConfig();
        config.setName("beerDb");
        DataSourceConfig sqLite = new DataSourceConfig();

        sqLite.setDriver("org.sqlite.JDBC");
        sqLite.setUsername("mluukkai");
        sqLite.setPassword("mluukkai");
        sqLite.setUrl(tietokantaUrl); //"jdbc:sqlite:beer.db"
        config.setDataSourceConfig(sqLite);
        config.setDatabasePlatform(new SQLitePlatform());
        config.getDataSourceConfig().setIsolationLevel(Transaction.READ_UNCOMMITTED);

        config.setDefaultServer(false);
        config.setRegister(false);

        config.addClass(Beer.class);
        config.addClass(Brewery.class);
        config.addClass(User.class);
        config.addClass(Rating.class);

        if (dropAndCreate) {
            config.setDdlGenerate(true);
            config.setDdlRun(true);
            //config.setDebugSql(true);
        }

        // konstruktorin parametrina annettavat hallinnoitavat luokat lisätään seuraavasti
        for (Class luokka : luokat) {
            config.addClass(luokka);
        }

        server = EbeanServerFactory.create(config);
    }


    // muut metodit
    // apumetodi, jonka avulla Application-olio pääsee aluksi käsiksi EbeanServer-olioon
    public EbeanServer getServer() {
        return server;
    }

    @Override
    public void saveObject(Object o) {
        server.save(o);
    }

    @Override
    public <T> T findWithName(Class<T> type, String name) {
        return server.find(type).where().like("name", name).findUnique();
    }

    @Override
    public <T> List<T> findAll(Class<T> type) {
        return server.find(type).findList();
    }

    @Override
    public <T> List<T> findAllOrdered(Class<T> type, String orderedBy) {
         return server.find(type).orderBy(orderedBy).findList();
    }
    

}