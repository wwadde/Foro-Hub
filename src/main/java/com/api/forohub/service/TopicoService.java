package com.api.forohub.service;

import com.api.forohub.domain.topico.TopicoDatos;
import com.api.forohub.repository.TopicoEntity;
import com.api.forohub.repository.TopicoRepository;
import com.api.forohub.repository.UsuarioEntity;
import com.api.forohub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;



    public void guardarTopico(TopicoDatos topico) {

        UsuarioEntity usuario = usuarioRepository.findById(topico.usuarioId()).orElseThrow();
        TopicoEntity topicoEntity = new TopicoEntity(topico,usuario);
        topicoRepository.save(topicoEntity);



    }



    public Page<TopicoDatos> listarTopicos(Pageable paginacion) {
        return topicoRepository.listarTopicosActivos(paginacion).map(TopicoDatos::new);
    }

    public TopicoDatos obtenerTopico(Long id) {
        return topicoRepository.findById(id).map(TopicoDatos::new).orElseThrow();
    }

    public TopicoDatos actualizarTopico(Long id, TopicoDatos topico) {

        Optional<TopicoEntity> topicoOptional = topicoRepository.findById(id);

        if(topicoOptional.isPresent()){
            TopicoEntity topicoEntity = topicoOptional.get();
            topicoEntity.setTitulo(topico.titulo());
            topicoEntity.setMensaje(topico.mensaje());
            topicoEntity.setFechaCreacion(topico.fechaCreacion());
            topicoEntity.setStatus(topico.status());
            topicoRepository.save(topicoEntity);
            return new TopicoDatos(topicoEntity);

    }
        return null;
}


    public TopicoDatos eliminarTopico(Long id) {

        Optional<TopicoEntity> topicoOptional = topicoRepository.findById(id);
        if(topicoOptional.isPresent()){
            TopicoEntity topicoEntity = topicoOptional.get();
            topicoEntity.setStatus(false);
            topicoRepository.save(topicoEntity);
            return new TopicoDatos(topicoEntity);
        }
        return null;
    }
}
