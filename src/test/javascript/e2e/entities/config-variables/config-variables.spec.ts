/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ConfigVariablesComponentsPage, ConfigVariablesDeleteDialog, ConfigVariablesUpdatePage } from './config-variables.page-object';

const expect = chai.expect;

describe('ConfigVariables e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let configVariablesUpdatePage: ConfigVariablesUpdatePage;
    let configVariablesComponentsPage: ConfigVariablesComponentsPage;
    let configVariablesDeleteDialog: ConfigVariablesDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load ConfigVariables', async () => {
        await navBarPage.goToEntity('config-variables');
        configVariablesComponentsPage = new ConfigVariablesComponentsPage();
        await browser.wait(ec.visibilityOf(configVariablesComponentsPage.title), 5000);
        expect(await configVariablesComponentsPage.getTitle()).to.eq('jhipsterpressApp.configVariables.home.title');
    });

    it('should load create ConfigVariables page', async () => {
        await configVariablesComponentsPage.clickOnCreateButton();
        configVariablesUpdatePage = new ConfigVariablesUpdatePage();
        expect(await configVariablesUpdatePage.getPageTitle()).to.eq('jhipsterpressApp.configVariables.home.createOrEditLabel');
        await configVariablesUpdatePage.cancel();
    });

    it('should create and save ConfigVariables', async () => {
        const nbButtonsBeforeCreate = await configVariablesComponentsPage.countDeleteButtons();

        await configVariablesComponentsPage.clickOnCreateButton();
        await promise.all([
            configVariablesUpdatePage.setConfigVarLong1Input('5'),
            configVariablesUpdatePage.setConfigVarLong2Input('5'),
            configVariablesUpdatePage.setConfigVarLong3Input('5'),
            configVariablesUpdatePage.setConfigVarLong4Input('5'),
            configVariablesUpdatePage.setConfigVarLong5Input('5'),
            configVariablesUpdatePage.setConfigVarLong6Input('5'),
            configVariablesUpdatePage.setConfigVarLong7Input('5'),
            configVariablesUpdatePage.setConfigVarLong8Input('5'),
            configVariablesUpdatePage.setConfigVarLong9Input('5'),
            configVariablesUpdatePage.setConfigVarLong10Input('5'),
            configVariablesUpdatePage.setConfigVarLong11Input('5'),
            configVariablesUpdatePage.setConfigVarLong12Input('5'),
            configVariablesUpdatePage.setConfigVarLong13Input('5'),
            configVariablesUpdatePage.setConfigVarLong14Input('5'),
            configVariablesUpdatePage.setConfigVarLong15Input('5'),
            configVariablesUpdatePage.setConfigVarString19Input('configVarString19'),
            configVariablesUpdatePage.setConfigVarString20Input('configVarString20')
        ]);
        expect(await configVariablesUpdatePage.getConfigVarLong1Input()).to.eq('5');
        expect(await configVariablesUpdatePage.getConfigVarLong2Input()).to.eq('5');
        expect(await configVariablesUpdatePage.getConfigVarLong3Input()).to.eq('5');
        expect(await configVariablesUpdatePage.getConfigVarLong4Input()).to.eq('5');
        expect(await configVariablesUpdatePage.getConfigVarLong5Input()).to.eq('5');
        expect(await configVariablesUpdatePage.getConfigVarLong6Input()).to.eq('5');
        expect(await configVariablesUpdatePage.getConfigVarLong7Input()).to.eq('5');
        expect(await configVariablesUpdatePage.getConfigVarLong8Input()).to.eq('5');
        expect(await configVariablesUpdatePage.getConfigVarLong9Input()).to.eq('5');
        expect(await configVariablesUpdatePage.getConfigVarLong10Input()).to.eq('5');
        expect(await configVariablesUpdatePage.getConfigVarLong11Input()).to.eq('5');
        expect(await configVariablesUpdatePage.getConfigVarLong12Input()).to.eq('5');
        expect(await configVariablesUpdatePage.getConfigVarLong13Input()).to.eq('5');
        expect(await configVariablesUpdatePage.getConfigVarLong14Input()).to.eq('5');
        expect(await configVariablesUpdatePage.getConfigVarLong15Input()).to.eq('5');
        const selectedConfigVarBoolean16 = configVariablesUpdatePage.getConfigVarBoolean16Input();
        if (await selectedConfigVarBoolean16.isSelected()) {
            await configVariablesUpdatePage.getConfigVarBoolean16Input().click();
            expect(await configVariablesUpdatePage.getConfigVarBoolean16Input().isSelected()).to.be.false;
        } else {
            await configVariablesUpdatePage.getConfigVarBoolean16Input().click();
            expect(await configVariablesUpdatePage.getConfigVarBoolean16Input().isSelected()).to.be.true;
        }
        const selectedConfigVarBoolean17 = configVariablesUpdatePage.getConfigVarBoolean17Input();
        if (await selectedConfigVarBoolean17.isSelected()) {
            await configVariablesUpdatePage.getConfigVarBoolean17Input().click();
            expect(await configVariablesUpdatePage.getConfigVarBoolean17Input().isSelected()).to.be.false;
        } else {
            await configVariablesUpdatePage.getConfigVarBoolean17Input().click();
            expect(await configVariablesUpdatePage.getConfigVarBoolean17Input().isSelected()).to.be.true;
        }
        const selectedConfigVarBoolean18 = configVariablesUpdatePage.getConfigVarBoolean18Input();
        if (await selectedConfigVarBoolean18.isSelected()) {
            await configVariablesUpdatePage.getConfigVarBoolean18Input().click();
            expect(await configVariablesUpdatePage.getConfigVarBoolean18Input().isSelected()).to.be.false;
        } else {
            await configVariablesUpdatePage.getConfigVarBoolean18Input().click();
            expect(await configVariablesUpdatePage.getConfigVarBoolean18Input().isSelected()).to.be.true;
        }
        expect(await configVariablesUpdatePage.getConfigVarString19Input()).to.eq('configVarString19');
        expect(await configVariablesUpdatePage.getConfigVarString20Input()).to.eq('configVarString20');
        await configVariablesUpdatePage.save();
        expect(await configVariablesUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await configVariablesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last ConfigVariables', async () => {
        const nbButtonsBeforeDelete = await configVariablesComponentsPage.countDeleteButtons();
        await configVariablesComponentsPage.clickOnLastDeleteButton();

        configVariablesDeleteDialog = new ConfigVariablesDeleteDialog();
        expect(await configVariablesDeleteDialog.getDialogTitle()).to.eq('jhipsterpressApp.configVariables.delete.question');
        await configVariablesDeleteDialog.clickOnConfirmButton();

        expect(await configVariablesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
