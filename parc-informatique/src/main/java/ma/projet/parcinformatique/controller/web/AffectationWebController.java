package ma.projet.parcinformatique.controller.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.projet.parcinformatique.dto.AffectationForm;
import ma.projet.parcinformatique.entity.Affectation;
import ma.projet.parcinformatique.enums.StatutAffectation;
import ma.projet.parcinformatique.service.AffectationService;
import ma.projet.parcinformatique.service.EmployeService;
import ma.projet.parcinformatique.service.MaterielService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/affectations")
@RequiredArgsConstructor
public class AffectationWebController {

    @ModelAttribute("activeTab")
    public String activeTab() {
        return "affectations";
    }

    private final AffectationService affectationService;
    private final MaterielService materielService;
    private final EmployeService employeService;

    @GetMapping
    public String listAffectations(
            @RequestParam(required = false) StatutAffectation statut,
            @RequestParam(required = false) String service,
            Model model) {

        List<Affectation> list;
        if (statut != null && service != null && !service.isBlank()) {
            list = affectationService.findByStatutAndService(statut, service);
        } else if (statut != null) {
            list = affectationService.findByStatut(statut);
        } else if (service != null && !service.isBlank()) {
            list = affectationService.findByService(service);
        } else {
            list = affectationService.findAll();
        }

        model.addAttribute("affectations", list);
        model.addAttribute("statuts", StatutAffectation.values());
        model.addAttribute("selectedStatut", statut);
        model.addAttribute("selectedService", service);
        return "affectations/list";
    }

    @GetMapping("/new")
    public String newAffectationForm(Model model) {
        AffectationForm form = new AffectationForm();
        form.setDateDebut(LocalDate.now());
        form.setStatut(StatutAffectation.ACTIVE);

        model.addAttribute("affectation", form);
        model.addAttribute("materiels", materielService.findAll());
        model.addAttribute("employes", employeService.findAll());
        model.addAttribute("isEdit", false);
        return "affectations/form";
    }

    @PostMapping("/new")
    public String saveAffectation(
            @Valid @ModelAttribute("affectation") AffectationForm form,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("materiels", materielService.findAll());
            model.addAttribute("employes", employeService.findAll());
            model.addAttribute("isEdit", false);
            return "affectations/form";
        }

        try {
            affectationService.affecter(form.getMaterielId(), form.getEmployeId(), form.getDateDebut());
            redirectAttributes.addFlashAttribute("successMsg", "Le matériel a été affecté avec succès !");
            return "redirect:/affectations";
        } catch (Exception e) {
            model.addAttribute("materiels", materielService.findAll());
            model.addAttribute("employes", employeService.findAll());
            model.addAttribute("isEdit", false);
            model.addAttribute("errorMsg", e.getMessage());
            return "affectations/form";
        }
    }

    @GetMapping("/edit/{id}")
    public String editAffectationForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Affectation aff = affectationService.findById(id);
            AffectationForm form = AffectationForm.builder()
                    .id(aff.getId())
                    .materielId(aff.getMateriel().getId())
                    .employeId(aff.getEmploye().getId())
                    .dateDebut(aff.getDateDebut())
                    .dateFin(aff.getDateFin())
                    .statut(aff.getStatut())
                    .build();

            model.addAttribute("affectation", form);
            model.addAttribute("materiels", materielService.findAll());
            model.addAttribute("employes", employeService.findAll());
            model.addAttribute("statuts", StatutAffectation.values());
            model.addAttribute("isEdit", true);
            return "affectations/form";
        } catch (NoSuchElementException e) {
            redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());
            return "redirect:/affectations";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateAffectation(
            @PathVariable Long id,
            @Valid @ModelAttribute("affectation") AffectationForm form,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("materiels", materielService.findAll());
            model.addAttribute("employes", employeService.findAll());
            model.addAttribute("statuts", StatutAffectation.values());
            model.addAttribute("isEdit", true);
            return "affectations/form";
        }

        try {
            affectationService.update(id, form.getMaterielId(), form.getEmployeId(), form.getDateDebut(), form.getDateFin(), form.getStatut());
            redirectAttributes.addFlashAttribute("successMsg", "L'affectation a été mise à jour avec succès !");
            return "redirect:/affectations";
        } catch (Exception e) {
            model.addAttribute("materiels", materielService.findAll());
            model.addAttribute("employes", employeService.findAll());
            model.addAttribute("statuts", StatutAffectation.values());
            model.addAttribute("isEdit", true);
            model.addAttribute("errorMsg", e.getMessage());
            return "affectations/form";
        }
    }

    @GetMapping("/cloturer/{id}")
    public String cloturerAffectation(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            affectationService.cloturerAffectation(id);
            redirectAttributes.addFlashAttribute("successMsg", "L'affectation a été clôturée avec succès !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());
        }
        return "redirect:/affectations";
    }

    @GetMapping("/delete/{id}")
    public String deleteAffectation(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            affectationService.delete(id);
            redirectAttributes.addFlashAttribute("successMsg", "L'affectation a été supprimée avec succès !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());
        }
        return "redirect:/affectations";
    }
}
