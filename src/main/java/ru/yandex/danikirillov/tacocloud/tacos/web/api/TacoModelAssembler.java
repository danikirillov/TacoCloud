package ru.yandex.danikirillov.tacocloud.tacos.web.api;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import ru.yandex.danikirillov.tacocloud.tacos.Taco;

public class TacoModelAssembler extends RepresentationModelAssemblerSupport<Taco, TacoRepresentationModel> {

    public TacoModelAssembler() {
        super(DesignTacoController.class, TacoRepresentationModel.class);
    }

    @Override
    protected TacoRepresentationModel instantiateModel(Taco taco) {
        return new TacoRepresentationModel(taco);
    }

    @Override
    public TacoRepresentationModel toModel(Taco taco) {
        return createModelWithId(taco.getId(), taco);
    }
}
