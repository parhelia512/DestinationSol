package com.miloshpetrov.sol2.menu;

import com.badlogic.gdx.*;
import com.miloshpetrov.sol2.SolCmp;
import com.miloshpetrov.sol2.ui.*;

import java.util.ArrayList;
import java.util.List;

public class OptionsScreen implements SolUiScreen {
  private final ArrayList<SolUiControl> myControls;
  private final SolUiControl myHalpCtrl;
  private final SolUiControl myBackCtrl;
  private final SolUiControl myResoCtrl;

  public OptionsScreen(MenuLayout menuLayout, boolean mobile) {

    myControls = new ArrayList<SolUiControl>();

    myResoCtrl = new SolUiControl(menuLayout.buttonRect(-1, 0));
    myResoCtrl.setDisplayName("Resolution");
    myResoCtrl.setEnabled(Gdx.app.getType() == Application.ApplicationType.Desktop);
    myControls.add(myResoCtrl);

    myHalpCtrl = new SolUiControl(menuLayout.buttonRect(-1, 1));
    myHalpCtrl.setDisplayName("Controls");
    myHalpCtrl.setEnabled(!mobile);
    myControls.add(myHalpCtrl);

    myBackCtrl = new SolUiControl(menuLayout.buttonRect(-1, 2), Input.Keys.ESCAPE);
    myBackCtrl.setDisplayName("Back");
    myControls.add(myBackCtrl);
  }

  @Override
  public List<SolUiControl> getControls() {
    return myControls;
  }

  @Override
  public void updateCustom(SolCmp cmp, SolInputMan.Ptr[] ptrs) {
    SolInputMan im = cmp.getInputMan();
    MenuScreens screens = cmp.getMenuScreens();
    if (myResoCtrl.isJustOff()) {
      im.setScreen(cmp, screens.resoScreen);
    }
    if (myHalpCtrl.isJustOff()) {
      im.setScreen(cmp, screens.halpScreen);
    }
    if (myBackCtrl.isJustOff()) {
      im.setScreen(cmp, screens.main);
    }
  }

  @Override
  public void drawPre(UiDrawer uiDrawer, SolCmp cmp) {

  }

  @Override
  public boolean isCursorOnBg(SolInputMan.Ptr ptr) {
    return false;
  }

  @Override
  public void onAdd(SolCmp cmp) {

  }

  @Override
  public void drawPost(UiDrawer uiDrawer, SolCmp cmp) {
  }
}