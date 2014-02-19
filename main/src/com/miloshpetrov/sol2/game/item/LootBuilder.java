package com.miloshpetrov.sol2.game.item;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.miloshpetrov.sol2.TexMan;
import com.miloshpetrov.sol2.common.Col;
import com.miloshpetrov.sol2.game.SolGame;
import com.miloshpetrov.sol2.game.dra.*;
import com.miloshpetrov.sol2.game.particle.LightSrc;

import java.util.ArrayList;
import java.util.List;

public class LootBuilder {
  public static final float SZ = .12f;

  public LootBuilder() {
  }

  // set spd & rot spd
  public Loot build(SolGame game, Vector2 pos, SolItem item, Vector2 spd, int life, float rotSpd) {
    String name = item.getTexName();
    List<Dra> dras = new ArrayList<Dra>();
    TexMan texMan = game.getTexMan();
    TextureAtlas.AtlasRegion tex = texMan.getTex(TexMan.ICONS_DIR + name);
    RectSprite s = new RectSprite(tex, SZ, 0, 0, new Vector2(), DraLevel.GUNS, 0, 0, Col.W);
    dras.add(s);
    Body b = buildBody(game, pos);
    b.setLinearVelocity(spd);
    b.setAngularVelocity(rotSpd);
    LightSrc ls = new LightSrc(game, .3f, false, .5f, new Vector2());
    ls.collectDras(dras);
    Loot loot = new Loot(item, b, life, dras, ls);
    b.setUserData(loot);
    return loot;
  }

  private Body buildBody(SolGame game, Vector2 pos) {
    BodyDef bd = new BodyDef();
    bd.type = BodyDef.BodyType.DynamicBody;
    bd.angle = 0;
    bd.angularDamping = 0;
    bd.position.set(pos);
    bd.linearDamping = 0;
    Body body = game.getObjMan().getWorld().createBody(bd);
    PolygonShape shape = new PolygonShape();
    shape.setAsBox(SZ/2, SZ/2);
    body.createFixture(shape, .5f);
    shape.dispose();
    return body;
  }
}