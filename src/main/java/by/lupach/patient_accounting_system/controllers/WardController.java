package by.lupach.patient_accounting_system.controllers;

import by.lupach.patient_accounting_system.entities.Ward;
import by.lupach.patient_accounting_system.services.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class WardController {

    @Autowired
    private WardService wardService;

    // Метод для создания новой палаты
    @GetMapping("/create-ward")
    public String createWardForm(Model model) {
        model.addAttribute("ward", new Ward());
        return "create_ward";
    }

    @PostMapping("/create-ward")
    public String createWard(@ModelAttribute Ward ward) {
        wardService.save(ward);
        return "redirect:/wards";
    }

    // Метод для отображения списка палат
    @GetMapping("/wards")
    public String listWards(Model model) {
        List<Ward> wards = wardService.getAll().get();
        model.addAttribute("wards", wards);
        return "wards";
    }

    // Метод для редактирования палаты
    @GetMapping("/edit-ward/{id}")
    public String editWardForm(@PathVariable("id") Integer id, Model model) {
        Optional<Ward> ward = wardService.getById(id);
        if (ward.isPresent()) {
            model.addAttribute("ward", ward.get());
            return "edit_ward";
        } else {
            return "redirect:/wards"; // если палата не найдена, редирект на список
        }
    }

    @PostMapping("/edit-ward/{id}")
    public String updateWard(@PathVariable("id") Integer id, @ModelAttribute Ward ward) {
        ward.setId(id); // Устанавливаем ID для существующей палаты
        wardService.save(ward); // Сохраняем обновленную палату
        return "redirect:/wards";
    }

    // Метод для удаления палаты
    @GetMapping("/delete-ward/{id}")
    public String deleteWard(@PathVariable("id") Integer id) {
        wardService.deleteById(id);
        return "redirect:/wards";
    }
}
