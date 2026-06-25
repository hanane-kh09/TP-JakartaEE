package ma.projet.parcinformatique.controller.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.projet.parcinformatique.entity.Affectation;
import ma.projet.parcinformatique.entity.Materiel;
import ma.projet.parcinformatique.enums.EtatMateriel;
import ma.projet.parcinformatique.enums.TypeMateriel;
import ma.projet.parcinformatique.service.AffectationService;
import ma.projet.parcinformatique.service.MaterielService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/materiels")
@RequiredArgsConstructor
public class MaterielWebController {

    @ModelAttribute("activeTab")
    public String activeTab() {
        return "materiels";
    }

    private final MaterielService materielService;
    private final AffectationService affectationService;

    @GetMapping
    public String listMateriels(@RequestParam(value = "search", required = false) String search, Model model) {
        List<Materiel> list = materielService.findAll();
        if (search != null && !search.isBlank()) {
            String query = search.toLowerCase().trim();
            list = list.stream()
                    .filter(m -> m.getRef().toLowerCase().contains(query) || m.getMarque().toLowerCase().contains(query))
                    .collect(Collectors.toList());
            model.addAttribute("search", search);
        }
        model.addAttribute("materiels", list);
        return "materiels/list";
    }

    @GetMapping("/new")
    public String newMaterielForm(Model model) {
        model.addAttribute("materiel", new Materiel());
        model.addAttribute("types", TypeMateriel.values());
        model.addAttribute("etats", EtatMateriel.values());
        model.addAttribute("isEdit", false);
        return "materiels/form";
    }

    @PostMapping("/new")
    public String saveMateriel(@Valid @ModelAttribute("materiel") Materiel materiel, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("types", TypeMateriel.values());
            model.addAttribute("etats", EtatMateriel.values());
            model.addAttribute("isEdit", false);
            return "materiels/form";
        }
        try {
            materielService.save(materiel);
            redirectAttributes.addFlashAttribute("successMsg", "Le matériel a été créé avec succès !");
            return "redirect:/materiels";
        } catch (IllegalArgumentException e) {
            model.addAttribute("types", TypeMateriel.values());
            model.addAttribute("etats", EtatMateriel.values());
            model.addAttribute("isEdit", false);
            model.addAttribute("errorMsg", e.getMessage());
            return "materiels/form";
        }
    }

    @GetMapping("/edit/{id}")
    public String editMaterielForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Materiel materiel = materielService.findById(id);
            model.addAttribute("materiel", materiel);
            model.addAttribute("types", TypeMateriel.values());
            model.addAttribute("etats", EtatMateriel.values());
            model.addAttribute("isEdit", true);
            return "materiels/form";
        } catch (NoSuchElementException e) {
            redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());
            return "redirect:/materiels";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateMateriel(@PathVariable Long id, @Valid @ModelAttribute("materiel") Materiel materiel, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("types", TypeMateriel.values());
            model.addAttribute("etats", EtatMateriel.values());
            model.addAttribute("isEdit", true);
            return "materiels/form";
        }
        try {
            materielService.update(id, materiel);
            redirectAttributes.addFlashAttribute("successMsg", "Le matériel a été mis à jour avec succès !");
            return "redirect:/materiels";
        } catch (Exception e) {
            model.addAttribute("types", TypeMateriel.values());
            model.addAttribute("etats", EtatMateriel.values());
            model.addAttribute("isEdit", true);
            model.addAttribute("errorMsg", e.getMessage());
            return "materiels/form";
        }
    }

    @GetMapping("/view/{id}")
    public String viewMateriel(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Materiel materiel = materielService.findById(id);
            List<Affectation> affectations = affectationService.findByMateriel(id);
            model.addAttribute("materiel", materiel);
            model.addAttribute("affectations", affectations);
            return "materiels/view";
        } catch (NoSuchElementException e) {
            redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());
            return "redirect:/materiels";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteMateriel(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            materielService.delete(id);
            redirectAttributes.addFlashAttribute("successMsg", "Le matériel a été supprimé avec succès !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());
        }
        return "redirect:/materiels";
    }
}
