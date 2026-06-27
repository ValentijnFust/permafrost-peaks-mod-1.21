package net.mod.permafrostpeaks.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.mod.permafrostpeaks.PermaFrostPeaks;
import net.mod.permafrostpeaks.entity.custom.TrollEntity;

public class TrollModel<T extends TrollEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer TROLL = new EntityModelLayer(Identifier.of(PermaFrostPeaks.MOD_ID, "troll"), "main");
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart troll;
    private final ModelPart torso;
    private final ModelPart arm;
    private final ModelPart leftarm;
    private final ModelPart rightarm;
    private final ModelPart ears;
    private final ModelPart leftear;
    private final ModelPart rightear;
    private final ModelPart legs;
    private final ModelPart leftleg;
    private final ModelPart rightleg;
    public TrollModel(ModelPart root) {
        this.root = root.getChild("root");
        this.troll = this.root.getChild("troll");
        this.torso = this.troll.getChild("torso");
        this.arm = this.torso.getChild("arm");
        this.leftarm = this.arm.getChild("leftarm");
        this.rightarm = this.arm.getChild("rightarm");
        this.head = this.torso.getChild("head");
        this.ears = this.head.getChild("ears");
        this.leftear = this.ears.getChild("leftear");
        this.rightear = this.ears.getChild("rightear");
        this.legs = this.troll.getChild("legs");
        this.leftleg = this.legs.getChild("leftleg");
        this.rightleg = this.legs.getChild("rightleg");


    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData troll = root.addChild("troll", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -2.0F, 2.0F));

        ModelPartData torso = troll.addChild("torso", ModelPartBuilder.create().uv(0, 6).cuboid(-2.0F, -7.0F, -2.0F, 5.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-2.0F, -5.0F, -1.0F, 5.0F, 3.0F, 3.0F, new Dilation(0.0F))
                .uv(8, 21).cuboid(-2.0F, -8.0F, -1.0F, 5.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(16, 23).cuboid(2.0F, -8.0F, -2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(24, 0).cuboid(-2.0F, -8.0F, -2.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 11).cuboid(-2.0F, -2.0F, -1.0F, 5.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -3.0F, -1.0F));

        ModelPartData arm = torso.addChild("arm", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 5.0F, -2.0F));

        ModelPartData leftarm = arm.addChild("leftarm", ModelPartBuilder.create().uv(16, 0).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, -11.0F, 1.0F));

        ModelPartData rightarm = arm.addChild("rightarm", ModelPartBuilder.create().uv(0, 16).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, -11.0F, 1.0F));

        ModelPartData head = torso.addChild("head", ModelPartBuilder.create().uv(14, 11).cuboid(-2.0F, -1.0F, -2.0F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, -8.0F, -3.0F));

        ModelPartData ears = head.addChild("ears", ModelPartBuilder.create(), ModelTransform.pivot(-1.0F, 13.0F, 1.0F));

        ModelPartData leftear = ears.addChild("leftear", ModelPartBuilder.create().uv(35, 2).cuboid(-3.0F, -15.0F, -2.0F, 1.0F, 3.0F, 0.0F, new Dilation(0.0F))
                .uv(35, 2).cuboid(-5.0F, -15.0F, -2.0F, 1.0F, 1.0F, 0.1F, new Dilation(0.0F))
                .uv(35, 2).cuboid(-2.0F, -14.0F, -2.0F, 1.0F, 2.0F, 0.1F, new Dilation(0.0F))
                .uv(35, 2).cuboid(-4.0F, -15.0F, -2.0F, 1.0F, 2.0F, 0.1F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 1.0F));

        ModelPartData rightear = ears.addChild("rightear", ModelPartBuilder.create().uv(35, 2).cuboid(3.0F, -15.0F, -2.0F, 1.0F, 3.0F, 0.0F, new Dilation(0.0F))
                .uv(35, 2).cuboid(5.0F, -15.0F, -2.0F, 1.0F, 1.0F, 0.1F, new Dilation(0.0F))
                .uv(35, 2).cuboid(2.0F, -14.0F, -2.0F, 1.0F, 2.0F, 0.1F, new Dilation(0.0F))
                .uv(35, 2).cuboid(4.0F, -15.0F, -2.0F, 1.0F, 2.0F, 0.1F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 1.0F));

        ModelPartData legs = troll.addChild("legs", ModelPartBuilder.create(), ModelTransform.pivot(4.0F, 2.0F, 0.0F));

        ModelPartData leftleg = legs.addChild("leftleg", ModelPartBuilder.create().uv(8, 17).cuboid(-1.0F, 3.0F, -2.0F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(20, 21).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, -4.0F, -1.0F));

        ModelPartData rightleg = legs.addChild("rightleg", ModelPartBuilder.create().uv(18, 17).cuboid(-1.0F, 3.0F, -2.0F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(8, 23).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, -4.0F, -1.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(TrollEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.animateMovement(TrollAnimations.ANIM_TROLL_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.updateAnimation(entity.idleAnimationState, TrollAnimations.ANIM_TROLL_IDLE, ageInTicks, 1f);
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);
// -30 30 -25 45
        this.head.yaw = headYaw * 0.017453292F;
        this.head.pitch = headPitch * 0.017453292F;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        root.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return root;
    }
}
