package com.naloga.AvtoIskalnik.Components;

import java.io.IOException;
import java.util.*;

import com.naloga.AvtoIskalnik.Model.Avto;
import com.naloga.AvtoIskalnik.Repository.AvtoRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WebCrawler {

    private static final Logger log = LoggerFactory.getLogger(WebCrawler.class);
    @Autowired
    private  AvtoRepository avtoRepository;


    @Scheduled(cron = "0 0 * * *")  // to naredi da ob polnoci enkrat dnevno gre
    public void crawl() throws IOException {
        log.info("Iskanje se zacne...");
        List<Avto> allAds = Stream.concat(crawlAvtoNet().stream(), crawlDoberAvtoSi().stream())
                .collect(Collectors.toList());
        avtoRepository.saveAll(allAds);
        markInactiveAds(allAds);
        log.info("Iskanje koncano.");
    }

    public List<Avto> crawlAvtoNet(){
        log.info("Crawling avto.net...");
        List<Avto> ads = new ArrayList<>();
        String url = "https://www.avto.net/rezultati.aspx";
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Elements avtoOglasi = document.select("div.container.m-0.mb-3");
        for (Element avtoOglas : avtoOglasi) {
            Avto ad = new Avto();
            ad.setSource("avto.net");
            Element znamka = avtoOglas.select("div.make").first();
            ad.setZnamka(znamka.text());

            Element model = avtoOglas.select("div.model").first();
            ad.setModel(model.text());

            Element cenaMin = avtoOglas.select("div.cenaMin").first();
            ad.setCenaMin(cenaMin.text());

            Element cenaMax = avtoOglas.select("div.cenaMax").first();
            ad.setCenaMax(cenaMax.text());

            Element prvaRegistracijaMin = avtoOglas.select("div.letnikMin").first();
            ad.setPrvaRegistracijaMin(prvaRegistracijaMin.text());

            Element prvaRegistracijaMax = avtoOglas.select("div.letnikMax").first();
            ad.setPrvaRegistracijaMax(prvaRegistracijaMax.text());

            Element tipGoriva = avtoOglas.select("div.bencin").first();
            ad.setTipGoriva(tipGoriva.text());

            Element menjalnik = avtoOglas.select("div.automatic").first();
            ad.setMenjalnik(menjalnik.text());

            Element prostorninaMotorjaMin = avtoOglas.select("div.ccmMin").first();
            ad.setTipGoriva(prostorninaMotorjaMin.text());

            Element prostorninaMotorjaMax = avtoOglas.select("div.ccmMax").first();
            ad.setTipGoriva(prostorninaMotorjaMax.text());

            Element mocMotorjaMin = avtoOglas.select("div.kwMIN").first();
            ad.setTipGoriva(mocMotorjaMin.text());

            Element mocMotorjaMax = avtoOglas.select("div.kwMAX").first();
            ad.setMocMotorjaMax(mocMotorjaMax.text());


            ad.setLastVisited(String.valueOf(LocalDateTime.now()));
            ad.setActive(true);
            ads.add(ad);
        }
        avtoRepository.saveAll(ads);
        return ads;
    }

    private List<Avto>crawlDoberAvtoSi() throws IOException {
        log.info("Crawling doberavto.si...");

        List<Avto> ads = new ArrayList<>();
        String url = "https://www.doberavto.si/iskanje?showFilters=true";
        Document document = Jsoup.connect(url).get();
        Elements avtoOglasi = document.select("div.car-card");
        for (Element avtoOglas : avtoOglasi) {
            Avto ad = new Avto();
            ad.setSource("doberavto.si");

            Element znamka = avtoOglas.select("#__BVID__4144").first();
            ad.setZnamka(znamka.text());

            Element model = avtoOglas.select("#__BVID__4145").first();
            ad.setModel(model.text());

            Element cenaMin = avtoOglas.select("#__BVID__4129").first();
            ad.setCenaMin(cenaMin.text());

            Element cenaMax = avtoOglas.select("#__BVID__4163").first();
            ad.setCenaMax(cenaMax.text());

            Element prvaRegistracijaMin = avtoOglas.select("#__BVID__4201").first();
            ad.setPrvaRegistracijaMin(prvaRegistracijaMin.text());

            Element prvaRegistracijaMax = avtoOglas.select("#__BVID__4261").first();
            ad.setPrvaRegistracijaMax(prvaRegistracijaMax.text());

            Element tipGoriva = avtoOglas.select("#__BVID__4166").first();
            ad.setTipGoriva(tipGoriva.text());

            Element menjalnik = avtoOglas.select("#__BVID__4167").first();
            ad.setTipGoriva(menjalnik.text());

            Element prostorninaMotorjaMin = avtoOglas.select("#__BVID__4168").first();
            ad.setTipGoriva(prostorninaMotorjaMin.text());

            Element prostorninaMotorjaMax = avtoOglas.select("#__BVID__4169").first();
            ad.setTipGoriva(prostorninaMotorjaMax.text());

            Element mocMotorjaMin = avtoOglas.select("#__BVID__4171").first();
            ad.setTipGoriva(mocMotorjaMin.text());

            Element mocMotorjaMax = avtoOglas.select("#__BVID__4172").first();
            ad.setMocMotorjaMax(mocMotorjaMax.text());


            ad.setLastVisited(String.valueOf(LocalDateTime.now()));
            ad.setActive(true);
            ads.add(ad);
        }

        return ads;
    }


    private void markInactiveAds(List<Avto> crawledAds) {
        log.info("Marking inactive ads...");
        List<Avto> activeAds = avtoRepository.findByActiveTrue();
        for (Avto ad : activeAds) {
            if (!crawledAds.contains(ad)) {
                ad.setActive(false);
                avtoRepository.save(ad);
            }
        }
        log.info("Oznacene {} reklame niso vec dejavne", activeAds.size() - crawledAds.size());
    }

}

