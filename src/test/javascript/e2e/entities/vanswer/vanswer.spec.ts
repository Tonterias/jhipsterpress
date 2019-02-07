/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { VanswerComponentsPage, VanswerDeleteDialog, VanswerUpdatePage } from './vanswer.page-object';

const expect = chai.expect;

describe('Vanswer e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let vanswerUpdatePage: VanswerUpdatePage;
    let vanswerComponentsPage: VanswerComponentsPage;
    /*let vanswerDeleteDialog: VanswerDeleteDialog;*/

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Vanswers', async () => {
        await navBarPage.goToEntity('vanswer');
        vanswerComponentsPage = new VanswerComponentsPage();
        await browser.wait(ec.visibilityOf(vanswerComponentsPage.title), 5000);
        expect(await vanswerComponentsPage.getTitle()).to.eq('jhipsterpressApp.vanswer.home.title');
    });

    it('should load create Vanswer page', async () => {
        await vanswerComponentsPage.clickOnCreateButton();
        vanswerUpdatePage = new VanswerUpdatePage();
        expect(await vanswerUpdatePage.getPageTitle()).to.eq('jhipsterpressApp.vanswer.home.createOrEditLabel');
        await vanswerUpdatePage.cancel();
    });

    /* it('should create and save Vanswers', async () => {
        const nbButtonsBeforeCreate = await vanswerComponentsPage.countDeleteButtons();

        await vanswerComponentsPage.clickOnCreateButton();
        await promise.all([
            vanswerUpdatePage.setCreationDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            vanswerUpdatePage.setUrlVanswerInput('urlVanswer'),
            vanswerUpdatePage.userSelectLastOption(),
            vanswerUpdatePage.vquestionSelectLastOption(),
        ]);
        expect(await vanswerUpdatePage.getCreationDateInput()).to.contain('2001-01-01T02:30');
        expect(await vanswerUpdatePage.getUrlVanswerInput()).to.eq('urlVanswer');
        const selectedAccepted = vanswerUpdatePage.getAcceptedInput();
        if (await selectedAccepted.isSelected()) {
            await vanswerUpdatePage.getAcceptedInput().click();
            expect(await vanswerUpdatePage.getAcceptedInput().isSelected()).to.be.false;
        } else {
            await vanswerUpdatePage.getAcceptedInput().click();
            expect(await vanswerUpdatePage.getAcceptedInput().isSelected()).to.be.true;
        }
        await vanswerUpdatePage.save();
        expect(await vanswerUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await vanswerComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });*/

    /* it('should delete last Vanswer', async () => {
        const nbButtonsBeforeDelete = await vanswerComponentsPage.countDeleteButtons();
        await vanswerComponentsPage.clickOnLastDeleteButton();

        vanswerDeleteDialog = new VanswerDeleteDialog();
        expect(await vanswerDeleteDialog.getDialogTitle())
            .to.eq('jhipsterpressApp.vanswer.delete.question');
        await vanswerDeleteDialog.clickOnConfirmButton();

        expect(await vanswerComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
