package tests;

import base.TestBase;
import enumerators.SinType;
import models.Sin;
import org.junit.*;
import pages.SinCityPage;
import pages.SpartaPage;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class SinCityTest extends TestBase {

    @Test
    public void testNewSin() {
        getDriver().get(BASE_URL + "sincity.php");
        SinCityPage sinCityPage = new SinCityPage();
        Sin spiderSin = new Sin("zabil som roba","furby","zabil som ho lopatou");

        List<SinType> spiderSinTags = new ArrayList<>();
        spiderSinTags.add(SinType.BLACKMAIL);
        spiderSinTags.add(SinType.HIJACK);
        spiderSin.setTags(spiderSinTags);

        sinCityPage.openPage();
        sinCityPage.fillSinInformation(spiderSin);
        sinCityPage.markTag(spiderSin.getTags());
        sinCityPage.confessSin();
        sinCityPage.openSinDetail(spiderSin);
        sinCityPage.checkSinStatus(spiderSin);
        sinCityPage.openDetail(spiderSin);
        sinCityPage.checkDetail(spiderSin);
    }

    @Test
    public void testForgive() throws MalformedURLException {
        getDriver().get(BASE_URL + "sincity.php");
        SinCityPage sinCityPage = new SinCityPage();
        Sin evaSpieva = new Sin("spievam","Dusan Cinkota","spievam stale");

        sinCityPage.openPage();
        sinCityPage.fillSinInformation(evaSpieva);
        sinCityPage.confessSin();

        SpartaPage spartaPage = new SpartaPage();
        spartaPage.openPage();
        spartaPage.forgiveSin(evaSpieva);

        sinCityPage.openPage();
        sinCityPage.checkSinStatus(evaSpieva);
    }

}
