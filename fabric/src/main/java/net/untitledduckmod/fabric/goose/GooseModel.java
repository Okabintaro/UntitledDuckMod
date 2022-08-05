package net.untitledduckmod.fabric.goose;

import net.minecraft.util.Identifier;
import net.untitledduckmod.goose.GooseEntity;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import static net.untitledduckmod.duck.DuckModelIdentifiers.*;

public class GooseModel extends AnimatedGeoModel<GooseEntity> {

    @Override
    public Identifier getModelResource(GooseEntity object) {
        return GOOSE_MODEL_LOCATION;
    }

    @Override
    public Identifier getTextureResource(GooseEntity entity) {
        if (entity.isBaby()) {
            return GOSLING_TEXTURE;
        } else {
            if (entity.hasCustomName()) {
                String name = entity.getCustomName().getString().toLowerCase();
                switch (name) {
                    case "ping":
                        return PING_GOOSE_TEXTURE;
                    case "sus":
                        return SUS_GOOSE_TEXTURE;
                    case "untitled":
                        return UNTITLED_GOOSE_TEXTURE;
                }
            }
        }

        return entity.getVariant() == 0 ? GOOSE_TEXTURE : CANADIAN_GOOSE_TEXTURE;
    }

    @Override
    public Identifier getAnimationResource(GooseEntity animatable) {
        return GOOSE_ANIMATION_FILE_LOCATION;
    }

    @Override
    public void setLivingAnimations(GooseEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);

        if (entity.isBaby()) {
            IBone root = this.getAnimationProcessor().getBone("root");
            // TODO: Why are these checks needed in fabric? Maybe a bug in geckolib.
            if (root != null) {
                root.setScaleX(0.7f);
                root.setScaleY(0.7f);
                root.setScaleZ(0.7f);
            }
        }

        IBone head = this.getAnimationProcessor().getBone("head");
        if (entity.lookingAround() && head != null) {
            EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}
