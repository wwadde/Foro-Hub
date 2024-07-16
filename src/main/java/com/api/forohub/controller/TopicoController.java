package com.api.forohub.controller;


import com.api.forohub.domain.topico.TopicoDatos;
import com.api.forohub.service.TopicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;


@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
@Controller
@ResponseBody
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;


    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerTopico(@PathVariable Long id){
        return ResponseEntity.ok(topicoService.obtenerTopico(id));
    }

    @GetMapping
    public ResponseEntity<Page<?>> listarTopicos(@PageableDefault(size = 2, sort = {"fechaCreacion"}) Pageable paginacion){

        return ResponseEntity.ok(topicoService.listarTopicos(paginacion));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> agregarTopicos(@RequestBody @Valid TopicoDatos topico){

        topicoService.guardarTopico(topico);

        return ResponseEntity.ok(topico);

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> actualizarTopico(@PathVariable Long id, @RequestBody @Valid TopicoDatos topico){
        TopicoDatos updatedTopico = topicoService.actualizarTopico(id, topico);
        if (updatedTopico == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedTopico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> eliminarTopico(@PathVariable Long id){
        TopicoDatos topicoDatos = topicoService.eliminarTopico(id);
        if (topicoDatos == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(topicoDatos);
    }
}
