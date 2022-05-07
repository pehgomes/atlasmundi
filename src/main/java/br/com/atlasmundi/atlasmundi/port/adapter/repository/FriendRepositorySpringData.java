package br.com.atlasmundi.atlasmundi.port.adapter.repository;

import br.com.atlasmundi.atlasmundi.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FriendRepositorySpringData extends JpaRepository<Friend, FriendId> {

}
