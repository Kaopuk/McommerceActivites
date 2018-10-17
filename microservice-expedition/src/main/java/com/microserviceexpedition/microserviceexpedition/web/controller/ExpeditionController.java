package com.microserviceexpedition.microserviceexpedition.web.controller;

import com.microserviceexpedition.microserviceexpedition.dao.ExpeditionDao;
import com.microserviceexpedition.microserviceexpedition.model.Expedition;
import com.microserviceexpedition.microserviceexpedition.web.exceptions.ExpeditionNotFoundException;
import com.microserviceexpedition.microserviceexpedition.web.exceptions.ImpossibleAjouterExpeditionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ExpeditionController {

    @Autowired
    ExpeditionDao expeditionDao;
    Logger log = LoggerFactory.getLogger(this.getClass());


    @PostMapping("/expeditions")
    public ResponseEntity<Void> ajouterUneExpedition(@RequestBody Expedition expedition) {

        Expedition nouvelleExpedition = expeditionDao.save(expedition);

        if(nouvelleExpedition == null) throw new ImpossibleAjouterExpeditionException("Impossible d'ajouter cette expédition");

        TODO: /* return */
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }



    //Récuperer un expedition par son id
    @GetMapping(value = "/suivi/{idCommande}")
    public Optional<Expedition> etatExpedition(@PathVariable int idCommande) {

        Optional<Expedition> expedition = expeditionDao.findByIdCommandeLike(idCommande);
        if(!expedition.isPresent()) throw new ExpeditionNotFoundException("Cette expédition n'existe pas");



        return expedition;
    }

    @PutMapping("/expeditions/update")
    public void updateExpedition(@RequestBody Expedition expedition) {

        expeditionDao.save(expedition);
    }

}