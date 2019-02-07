/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { VthumbComponentsPage, VthumbDeleteDialog, VthumbUpdatePage } from './vthumb.page-object';

const expect = chai.expect;

describe('Vthumb e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let vthumbUpdatePage: VthumbUpdatePage;
    let vthumbComponentsPage: VthumbComponentsPage;
    /*let vthumbDeleteDialog: VthumbDeleteDialog;*/

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Vthumbs', async () => {
        await navBarPage.goToEntity('vthumb');
        vthumbComponentsPage = new VthumbComponentsPage();
        await browser.wait(ec.visibilityOf(vthumbComponentsPage.title), 5000);
        expect(await vthumbComponentsPage.getTitle()).to.eq('jhipsterpressApp.vthumb.home.title');
    });

    it('should load create Vthumb page', async () => {
        await vthumbComponentsPage.clickOnCreateButton();
        vthumbUpdatePage = new VthumbUpdatePage();
        expect(await vthumbUpdatePage.getPageTitle()).to.eq('jhipsterpressApp.vthumb.home.createOrEditLabel');
        await vthumbUpdatePage.cancel();
    });

    /* it('should create and save Vthumbs', async () => {
        const nbButtonsBeforeCreate = await vthumbComponentsPage.countDeleteButtons();

        await vthumbComponentsPage.clickOnCreateButton();
        await promise.all([
            vthumbUpdatePage.setCreationDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            vthumbUpdatePage.userSelectLastOption(),
            vthumbUpdatePage.vquestionSelectLastOption(),
            vthumbUpdatePage.vanswerSelectLastOption(),
        ]);
        expect(await vthumbUpdatePage.getCreationDateInput()).to.contain('2001-01-01T02:30');
        const selectedVthumbUp = vthumbUpdatePage.getVthumbUpInput();
        if (await selectedVthumbUp.isSelected()) {
            await vthumbUpdatePage.getVthumbUpInput().click();
            expect(await vthumbUpdatePage.getVthumbUpInput().isSelected()).to.be.false;
        } else {
            await vthumbUpdatePage.getVthumbUpInput().click();
            expect(await vthumbUpdatePage.getVthumbUpInput().isSelected()).to.be.true;
        }
        const selectedVthumbDown = vthumbUpdatePage.getVthumbDownInput();
        if (await selectedVthumbDown.isSelected()) {
            await vthumbUpdatePage.getVthumbDownInput().click();
            expect(await vthumbUpdatePage.getVthumbDownInput().isSelected()).to.be.false;
        } else {
            await vthumbUpdatePage.getVthumbDownInput().click();
            expect(await vthumbUpdatePage.getVthumbDownInput().isSelected()).to.be.true;
        }
        await vthumbUpdatePage.save();
        expect(await vthumbUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await vthumbComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });*/

    /* it('should delete last Vthumb', async () => {
        const nbButtonsBeforeDelete = await vthumbComponentsPage.countDeleteButtons();
        await vthumbComponentsPage.clickOnLastDeleteButton();

        vthumbDeleteDialog = new VthumbDeleteDialog();
        expect(await vthumbDeleteDialog.getDialogTitle())
            .to.eq('jhipsterpressApp.vthumb.delete.question');
        await vthumbDeleteDialog.clickOnConfirmButton();

        expect(await vthumbComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
