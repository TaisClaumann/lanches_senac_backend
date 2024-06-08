package com.backend_senac.lanches_senac_backend.services;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import com.backend_senac.lanches_senac_backend.beans.ValidadorCpf;
import com.backend_senac.lanches_senac_backend.domain.Funcionario;
import com.backend_senac.lanches_senac_backend.domain.dto.FuncionarioDto;
import com.backend_senac.lanches_senac_backend.repositories.FuncionarioRepository;
import com.backend_senac.lanches_senac_backend.services.exceptions.CpfInvalidoException;
import com.backend_senac.lanches_senac_backend.services.exceptions.RegistroJaCadastradoException;
import com.backend_senac.lanches_senac_backend.services.exceptions.RegistroNaoEncontradoException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Valid
@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final ValidadorCpf validadorCpf;
    private final FuncionarioRepository funcionarioRepository;

    @Transactional(rollbackFor = Exception.class)
    public void salvar(@NotNull FuncionarioDto funcionarioDto) {
        validarCpf(funcionarioDto.getCpf());
        verificarFuncionario(funcionarioDto);
        funcionarioRepository.save(new Funcionario(funcionarioDto));
    }

    @Transactional(rollbackFor = Exception.class)
    public void alterar(@NotNull Long id, @NotNull FuncionarioDto funcionarioDto) {
        buscarPorId(id);
        funcionarioDto.setId(id);
        validarCpf(funcionarioDto.getCpf());
        funcionarioRepository.save(new Funcionario(funcionarioDto));
    }

    private void verificarFuncionario(FuncionarioDto funcionarioDto) {
        if (funcionarioRepository.existsByNomeIgnoreCase(funcionarioDto.getNome())) {
            throw new RegistroJaCadastradoException("Já existe um funcionário com nome " + funcionarioDto.getNome() + " no banco de dados");
        }

        if (funcionarioRepository.existsByCpf(funcionarioDto.getCpf())) {
            throw new RegistroJaCadastradoException("Já existe um funcionário com cpf " + funcionarioDto.getCpf() + " no banco de dados");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void validarCpf(@NotBlank String cpf) {
        try {
            CPFValidator cpfValidator = validadorCpf.cpfValidator();
            cpfValidator.assertValid(cpf);
        } catch (InvalidStateException invalidStateException) {
            throw new CpfInvalidoException("CPF inválido - " + invalidStateException.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void excluir(@NotNull Long id) {
        buscarPorId(id);
        funcionarioRepository.deleteById(id);
    }

    public void buscarPorId(Long id) {
        funcionarioRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Funcionário " + id + " não encontrado!"));
    }

    public List<FuncionarioDto> listarTodos() {
        return funcionarioRepository.findAll().stream().map(FuncionarioDto::new).toList();
    }
}
