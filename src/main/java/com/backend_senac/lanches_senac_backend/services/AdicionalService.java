package com.backend_senac.lanches_senac_backend.services;

import com.backend_senac.lanches_senac_backend.domain.Adicional;
import com.backend_senac.lanches_senac_backend.domain.dto.AdicionalDto;
import com.backend_senac.lanches_senac_backend.repositories.AdicionalRepository;
import com.backend_senac.lanches_senac_backend.services.exceptions.RegistroJaCadastradoException;
import com.backend_senac.lanches_senac_backend.services.exceptions.RegistroNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdicionalService {

    private final AdicionalRepository adicionalRepository;

    @Transactional
    public Adicional salvar(AdicionalDto adicionalDto){
        Optional<Adicional> adicionalExistente = adicionalRepository.findByNome(adicionalDto.getNome());
        if (adicionalExistente.isPresent()) {
            throw new RegistroJaCadastradoException("Já existe um adicional com nome " + adicionalDto.getNome() + " no banco de dados");
        }
        return adicionalRepository.save(new Adicional(adicionalDto));
    }

    public Adicional buscarPorNome(String nome) {
        return adicionalRepository.findByNome(nome)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Adicional " + nome + " não encontrado!"));
    }

    public List<AdicionalDto> listarTodos(){
        return adicionalRepository.findAll().stream().map(AdicionalDto::new).toList();
    }

    public void excluir(String nome){
        Adicional adicional = buscarPorNome(nome);
        adicionalRepository.delete(adicional);
    }

    @Transactional
    public Adicional atualizar(String nome, AdicionalDto adicionalDto){
        Adicional adicional = buscarPorNome(nome);

        if (adicional.getNome().equalsIgnoreCase(adicionalDto.getNome())) {
            throw new RegistroJaCadastradoException("Já existe um adicional com esse nome no banco de dados");
        }
        adicional.setNome(adicionalDto.getNome());
        return adicionalRepository.save(adicional);
    }
}
