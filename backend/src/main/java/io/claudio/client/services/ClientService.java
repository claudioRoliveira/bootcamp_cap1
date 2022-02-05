package io.claudio.client.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.claudio.client.dto.ClientDTO;
import io.claudio.client.entities.Client;
import io.claudio.client.repositories.ClientRepository;
import io.claudio.client.services.exceptions.DatabaseException;
import io.claudio.client.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	public Page<ClientDTO> findAll(Pageable pageable) {
		Page<Client> clients = repository.findAll(pageable);
		return clients.map(c -> new ClientDTO(c));

	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		return new ClientDTO(obj.orElseThrow(() -> new ResourceNotFoundException("Not Found!")));

	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id não exise!");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de Integridade");
		}

	}

	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client entity = new Client();
		copyDTOtoEntity(dto, entity, false);
		entity = repository.save(entity);
		return new ClientDTO(entity);
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		try {
			Client entity = repository.getOne(id);
			copyDTOtoEntity(dto, entity, true);
			entity = repository.save(entity);
			return new ClientDTO(entity);
		} catch (EntityNotFoundException e) {
			return null;
		}
	}

	private void copyDTOtoEntity(ClientDTO dto, Client entity, boolean update) {
		
		if (update) {
			if (dto.getName() != null) {
				entity.setName(dto.getName());
			}else if(dto.getBirthDate()!= null) {
				entity.setBirthDate(dto.getBirthDate());
			}else if(dto.getChildren() > 0) {
				entity.setChildren(dto.getChildren());
			}else if(dto.getCpf() != null) {
				entity.setCpf(dto.getCpf());
			}else if(dto.getIncome()>0.0) {
				entity.setIncome(dto.getIncome());
			}
		}else {
			entity.setName(dto.getName());
			entity.setBirthDate(dto.getBirthDate());
			entity.setChildren(dto.getChildren());
			entity.setCpf(dto.getCpf());
			entity.setIncome(dto.getIncome());
		}
	}
}
