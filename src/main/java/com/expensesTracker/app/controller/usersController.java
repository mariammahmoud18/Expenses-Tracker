package com.expensesTracker.app.controller;

import com.expensesTracker.app.DTO.expensesDTO;
import com.expensesTracker.app.DTO.rolesDTO;
import com.expensesTracker.app.DTO.usersDTO;
import com.expensesTracker.app.entities.Roles;
import com.expensesTracker.app.entities.Users;
import com.expensesTracker.app.service.rolesService;
import com.expensesTracker.app.service.usersService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.util.stream.Stream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


@RestController
@RequestMapping("/tracker")
public class usersController {
    @Autowired
    private usersService service;

    //no reuqestbody is supported in GET
    @GetMapping("/users")
    public List<usersDTO> getAll(@RequestParam(required = false)  String username, @RequestParam(required = false)  String email){
        if(username!=null)
                return service.getUserByUsername(username);

        if(email!=null)
                return service.getUsersByEmail(email);
        return service.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public Optional<usersDTO> getById(@PathVariable int id){
        return service.getUserById(id);
    }

    @DeleteMapping("/users/{id}")
    public void deleteById(@PathVariable int id){
        service.deleteUser(id);
    }

    @PostMapping("/users")
    public usersDTO createUser(@Valid @RequestBody usersDTO user){
        return service.saveUser(user);
    }

    @GetMapping("/users/{id}/roles")
    public List<rolesDTO> getUserRoles(@PathVariable int id){
        return service.getUserRoles(id);
    }

    @GetMapping("/users/{id}/expenses")
    public List<expensesDTO> getUserExpenses(@PathVariable int id){
        return service.getUserExpenses(id);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<usersDTO> updateUser(@PathVariable int id, @RequestBody @Valid usersDTO dto) {
        usersDTO updatedUser = service.updateUser(id, dto);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/users/{id}/expenses/pdf-download")
    public ResponseEntity<byte[]> getUserExperiencePDF(@PathVariable int id) throws FileNotFoundException, DocumentException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        List<expensesDTO> expensesDTOS = service.getUserExpenses(id);
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("usersExpenses.pdf"));
        PdfWriter writer = PdfWriter.getInstance(document, out);
        document.open();

        PdfPTable table = new PdfPTable(5);
        addTableHeader(table);
        addRows(table, expensesDTOS);

        document.add(table);
        writer.flush();
        document.close();

        byte[] pdfBytes = out.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "userExpenses.pdf");
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Expense ID", "Description", "Amount", "Date", "Category")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table, List<expensesDTO> expensesDTOS) {
        for(expensesDTO expense : expensesDTOS){
            table.addCell(String.valueOf(expense.getId()));
            table.addCell(expense.getDescription());
            table.addCell(String.valueOf(expense.getAmount()));
            table.addCell(expense.getDate().toString());
            table.addCell(expense.getCatgeoryName());
        }
    }


}
