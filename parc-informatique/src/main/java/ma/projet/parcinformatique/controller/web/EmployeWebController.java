package ma.projet.parcinformatique.controller.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.projet.parcinformatique.entity.Affectation;
import ma.projet.parcinformatique.entity.Employe;
import ma.projet.parcinformatique.service.AffectationService;
import ma.projet.parcinformatique.service.EmployeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/employes")
@RequiredArgsConstructor
public class EmployeWebController {

    @ModelAttribute("activeTab")
    public String activeTab() {
        return "employes";
    }

    private final EmployeService employeService;
    private final AffectationService affectationService;

    @GetMapping
    public String listEmployes(@RequestParam(value = "search", required = false) String search, Model model) {
        List<Employe> list = employeService.findAll();
        if (search != null && !search.isBlank()) {
            String query = search.toLowerCase().trim();
            list = list.stream()
                    .filter(e -> e.getNom().toLowerCase().contains(query) || e.getService().toLowerCase().contains(query))
                    .collect(Collectors.toList());
            model.addAttribute("search", search);
        }
        model.addAttribute("employes", list);
        return "employes/list";
    }

    @GetMapping("/new")
    public String newEmployeForm(Model model) {
        model.addAttribute("employe", new Employe());
        model.addAttribute("isEdit", false);
        return "employes/form";
    }

    @PostMapping("/new")
    public String saveEmploye(@Valid @ModelAttribute("employe") Employe employe, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("isEdit", false);
            return "employes/form";
        }
        try {
            employeService.save(employe);
            redirectAttributes.addFlashAttribute("successMsg", "L'employé a été créé avec succès !");
            return "redirect:/employes";
        } catch (IllegalArgumentException e) {
            model.addAttribute("isEdit", false);
            model.addAttribute("errorMsg", e.getMessage());
            return "employes/form";
        }
    }

    @GetMapping("/edit/{id}")
    public String editEmployeForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Employe employe = employeService.findById(id);
            model.addAttribute("employe", employe);
            model.addAttribute("isEdit", true);
            return "employes/form";
        } catch (NoSuchElementException e) {
            redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());
            return "redirect:/employes";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateEmploye(@PathVariable Long id, @Valid @ModelAttribute("employe") Employe employe, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("isEdit", true);
            return "employes/form";
        }
        try {
            employeService.update(id, employe);
            redirectAttributes.addFlashAttribute("successMsg", "L'employé a été mis à jour avec succès !");
            return "redirect:/employes";
        } catch (Exception e) {
            model.addAttribute("isEdit", true);
            model.addAttribute("errorMsg", e.getMessage());
            return "employes/form";
        }
    }

    @GetMapping("/view/{id}")
    public String viewEmploye(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Employe employe = employeService.findById(id);
            List<Affectation> affectations = affectationService.findByEmploye(id);
            model.addAttribute("employe", employe);
            model.addAttribute("affectations", affectations);
            return "employes/view";
        } catch (NoSuchElementException e) {
            redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());
            return "redirect:/employes";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteEmploye(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            employeService.delete(id);
            redirectAttributes.addFlashAttribute("successMsg", "L'employé a été supprimé avec succès !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());
        }
        return "redirect:/employes";
    }
}
