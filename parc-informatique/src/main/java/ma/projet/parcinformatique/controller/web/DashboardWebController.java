package ma.projet.parcinformatique.controller.web;

import lombok.RequiredArgsConstructor;
import ma.projet.parcinformatique.entity.Affectation;
import ma.projet.parcinformatique.entity.Materiel;
import ma.projet.parcinformatique.service.AffectationService;
import ma.projet.parcinformatique.service.EmployeService;
import ma.projet.parcinformatique.service.MaterielService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class DashboardWebController {

    @ModelAttribute("activeTab")
    public String activeTab() {
        return "dashboard";
    }

    private final MaterielService materielService;
    private final EmployeService employeService;
    private final AffectationService affectationService;

    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model model) {
        long totalMateriel = materielService.getTotalMateriel();
        long totalEmploye = employeService.findAll().size();
        long totalAffectation = affectationService.findAll().size();

        Map<String, Object> utilisationStats = affectationService.getTauxUtilisation();
        double tauxUtilisation = (double) utilisationStats.getOrDefault("tauxUtilisationPourcentage", 0.0);

        List<Materiel> recentMateriels = materielService.findAll().stream()
                .sorted(Comparator.comparing(Materiel::getId).reversed())
                .limit(5)
                .collect(Collectors.toList());

        List<Affectation> recentAffectations = affectationService.findAll().stream()
                .sorted(Comparator.comparing(Affectation::getId).reversed())
                .limit(5)
                .collect(Collectors.toList());

        model.addAttribute("totalMateriel", totalMateriel);
        model.addAttribute("totalEmploye", totalEmploye);
        model.addAttribute("totalAffectation", totalAffectation);
        model.addAttribute("tauxUtilisation", String.format("%.1f", tauxUtilisation));
        model.addAttribute("recentMateriels", recentMateriels);
        model.addAttribute("recentAffectations", recentAffectations);

        return "dashboard";
    }
}
