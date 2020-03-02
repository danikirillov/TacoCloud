package ru.yandex.danikirillov.tacocloud.tacos.web.api;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import ru.yandex.danikirillov.tacocloud.tacos.Taco;
import ru.yandex.danikirillov.tacocloud.tacos.data.TacoRepository;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RepositoryRestController
public class RecentTacosController {
    private TacoRepository tacoRepository;

    public RecentTacosController(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }


    @GetMapping(path = "/tacos/recent", produces = "application/hal+json")//hypertext application language
    //ResponseEntity - тк RepositoryRestController не схож с RestController, то есть методы по умолчанию не подписаны как @ResponseBody
    public ResponseEntity<CollectionModel<TacoRepresentationModel>> recentTacos() {
        PageRequest pageRequest = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        List<Taco> tacos = tacoRepository.findAll(pageRequest).getContent();

        CollectionModel<TacoRepresentationModel> recentResources = new TacoModelAssembler().toCollectionModel(tacos);
        recentResources.add(
                linkTo(methodOn(RecentTacosController.class).recentTacos())
                        .withRel("recents"));
        return new ResponseEntity<>(recentResources, HttpStatus.OK);
    }
}
