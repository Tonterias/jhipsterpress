/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MessageComponentsPage, MessageDeleteDialog, MessageUpdatePage } from './message.page-object';

const expect = chai.expect;

describe('Message e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let messageUpdatePage: MessageUpdatePage;
    let messageComponentsPage: MessageComponentsPage;
    let messageDeleteDialog: MessageDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Messages', async () => {
        await navBarPage.goToEntity('message');
        messageComponentsPage = new MessageComponentsPage();
        await browser.wait(ec.visibilityOf(messageComponentsPage.title), 5000);
        expect(await messageComponentsPage.getTitle()).to.eq('jhipsterpressApp.message.home.title');
    });

    it('should load create Message page', async () => {
        await messageComponentsPage.clickOnCreateButton();
        messageUpdatePage = new MessageUpdatePage();
        expect(await messageUpdatePage.getPageTitle()).to.eq('jhipsterpressApp.message.home.createOrEditLabel');
        await messageUpdatePage.cancel();
    });

    it('should create and save Messages', async () => {
        const nbButtonsBeforeCreate = await messageComponentsPage.countDeleteButtons();

        await messageComponentsPage.clickOnCreateButton();
        await promise.all([
            messageUpdatePage.setCreationDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            messageUpdatePage.setMessageTextInput('messageText'),
            messageUpdatePage.senderSelectLastOption(),
            messageUpdatePage.receiverSelectLastOption()
        ]);
        expect(await messageUpdatePage.getCreationDateInput()).to.contain('2001-01-01T02:30');
        expect(await messageUpdatePage.getMessageTextInput()).to.eq('messageText');
        const selectedIsDelivered = messageUpdatePage.getIsDeliveredInput();
        if (await selectedIsDelivered.isSelected()) {
            await messageUpdatePage.getIsDeliveredInput().click();
            expect(await messageUpdatePage.getIsDeliveredInput().isSelected()).to.be.false;
        } else {
            await messageUpdatePage.getIsDeliveredInput().click();
            expect(await messageUpdatePage.getIsDeliveredInput().isSelected()).to.be.true;
        }
        await messageUpdatePage.save();
        expect(await messageUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await messageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Message', async () => {
        const nbButtonsBeforeDelete = await messageComponentsPage.countDeleteButtons();
        await messageComponentsPage.clickOnLastDeleteButton();

        messageDeleteDialog = new MessageDeleteDialog();
        expect(await messageDeleteDialog.getDialogTitle()).to.eq('jhipsterpressApp.message.delete.question');
        await messageDeleteDialog.clickOnConfirmButton();

        expect(await messageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
