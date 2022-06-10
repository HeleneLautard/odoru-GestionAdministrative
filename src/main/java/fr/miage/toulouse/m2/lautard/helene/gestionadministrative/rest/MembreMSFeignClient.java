package fr.miage.toulouse.m2.lautard.helene.gestionadministrative.rest;

import fr.miage.toulouse.m2.lautard.helene.gestionadministrative.DTO.Adherent;
import fr.miage.toulouse.m2.lautard.helene.gestionadministrative.DTO.Enseignant;
import fr.miage.toulouse.m2.lautard.helene.gestionadministrative.DTO.Participant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value="gestionMembre")
public interface MembreMSFeignClient {
    @RequestMapping(method = RequestMethod.GET,
            value="/membres/adherents/{id}",
            produces= "application/json")
    Participant getAdherent(@PathVariable Long id);

    @RequestMapping(method = RequestMethod.GET,
            value="/membres/enseignants/{id}",
            produces= "application/json")
    Enseignant getEnseignant(@PathVariable Long id);


    @RequestMapping(method = RequestMethod.GET,
            value="/membres/adherents/niveau/{niveau}",
            produces= "application/json")
    List<Participant> getParticipantsNiveau(@PathVariable int niveau);

}
