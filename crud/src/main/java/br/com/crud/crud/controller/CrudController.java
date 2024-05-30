package br.com.crud.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crud.crud.model.CrudModel;
import br.com.crud.crud.repository.CrudRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/bd")
public class CrudController {
    @Autowired
    CrudRepository crudRepository;

    @PostMapping("/criar")
    public ResponseEntity<CrudModel> criaUsuario(@RequestBody CrudModel usuario) {
        CrudModel _usuario = crudRepository.save(usuario);
        return new ResponseEntity<>(_usuario, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<CrudModel>> getAllUsuarios(@RequestParam(required = false) String param) {
        List<CrudModel> usuarios = crudRepository.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUsuario(@PathVariable("id") long id) {
        try {
            crudRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pegar/{id}")
    public ResponseEntity<CrudModel> getUsuario(@PathVariable("id") long id) {
        Optional<CrudModel> usuario = crudRepository.findById(id);
        if (usuario.isPresent()) {
            return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/pegar/{id}")
    public ResponseEntity<CrudModel> putUsuario(@PathVariable long id, @RequestBody CrudModel usuario) {
        Optional<CrudModel> _usuario = crudRepository.findById(id);
        if (_usuario.isPresent()) {
            CrudModel usuarioCopia = _usuario.get();
            usuarioCopia.setNome(usuario.getNome());
            usuarioCopia.setSenha(usuario.getSenha());
            usuarioCopia.setCpf(usuario.getCpf());
            usuarioCopia.setDataNascimento(usuario.getDataNascimento());
            return new ResponseEntity<>(crudRepository.save(usuarioCopia), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
