package br.com.uninter.sghss.repositories;

import br.com.uninter.sghss.entities.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
}
