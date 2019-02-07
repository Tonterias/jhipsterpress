/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { UprofileComponentsPage, UprofileDeleteDialog, UprofileUpdatePage } from './uprofile.page-object';
import * as path from 'path';

const expect = chai.expect;

describe('Uprofile e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let uprofileUpdatePage: UprofileUpdatePage;
    let uprofileComponentsPage: UprofileComponentsPage;
    /*let uprofileDeleteDialog: UprofileDeleteDialog;*/
    const fileNameToUpload = 'logo-jhipster.png';
    const fileToUpload = '../../../../../main/webapp/content/images/' + fileNameToUpload;
    const absolutePath = path.resolve(__dirname, fileToUpload);

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Uprofiles', async () => {
        await navBarPage.goToEntity('uprofile');
        uprofileComponentsPage = new UprofileComponentsPage();
        await browser.wait(ec.visibilityOf(uprofileComponentsPage.title), 5000);
        expect(await uprofileComponentsPage.getTitle()).to.eq('jhipsterpressApp.uprofile.home.title');
    });

    it('should load create Uprofile page', async () => {
        await uprofileComponentsPage.clickOnCreateButton();
        uprofileUpdatePage = new UprofileUpdatePage();
        expect(await uprofileUpdatePage.getPageTitle()).to.eq('jhipsterpressApp.uprofile.home.createOrEditLabel');
        await uprofileUpdatePage.cancel();
    });

    /* it('should create and save Uprofiles', async () => {
        const nbButtonsBeforeCreate = await uprofileComponentsPage.countDeleteButtons();

        await uprofileComponentsPage.clickOnCreateButton();
        await promise.all([
            uprofileUpdatePage.setCreationDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            uprofileUpdatePage.setImageInput(absolutePath),
            uprofileUpdatePage.genderSelectLastOption(),
            uprofileUpdatePage.setPhoneInput('phone'),
            uprofileUpdatePage.setBioInput('bio'),
            uprofileUpdatePage.setFacebookInput('facebook'),
            uprofileUpdatePage.setTwitterInput('twitter'),
            uprofileUpdatePage.setLinkedinInput('linkedin'),
            uprofileUpdatePage.setInstagramInput('instagram'),
            uprofileUpdatePage.setGooglePlusInput('googlePlus'),
            uprofileUpdatePage.setBirthdateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            uprofileUpdatePage.civilStatusSelectLastOption(),
            uprofileUpdatePage.lookingForSelectLastOption(),
            uprofileUpdatePage.purposeSelectLastOption(),
            uprofileUpdatePage.physicalSelectLastOption(),
            uprofileUpdatePage.religionSelectLastOption(),
            uprofileUpdatePage.ethnicGroupSelectLastOption(),
            uprofileUpdatePage.studiesSelectLastOption(),
            uprofileUpdatePage.setSibblingsInput('5'),
            uprofileUpdatePage.eyesSelectLastOption(),
            uprofileUpdatePage.smokerSelectLastOption(),
            uprofileUpdatePage.childrenSelectLastOption(),
            uprofileUpdatePage.futureChildrenSelectLastOption(),
            uprofileUpdatePage.userSelectLastOption(),
        ]);
        expect(await uprofileUpdatePage.getCreationDateInput()).to.contain('2001-01-01T02:30');
        expect(await uprofileUpdatePage.getImageInput()).to.endsWith(fileNameToUpload);
        expect(await uprofileUpdatePage.getPhoneInput()).to.eq('phone');
        expect(await uprofileUpdatePage.getBioInput()).to.eq('bio');
        expect(await uprofileUpdatePage.getFacebookInput()).to.eq('facebook');
        expect(await uprofileUpdatePage.getTwitterInput()).to.eq('twitter');
        expect(await uprofileUpdatePage.getLinkedinInput()).to.eq('linkedin');
        expect(await uprofileUpdatePage.getInstagramInput()).to.eq('instagram');
        expect(await uprofileUpdatePage.getGooglePlusInput()).to.eq('googlePlus');
        expect(await uprofileUpdatePage.getBirthdateInput()).to.contain('2001-01-01T02:30');
        expect(await uprofileUpdatePage.getSibblingsInput()).to.eq('5');
        const selectedPet = uprofileUpdatePage.getPetInput();
        if (await selectedPet.isSelected()) {
            await uprofileUpdatePage.getPetInput().click();
            expect(await uprofileUpdatePage.getPetInput().isSelected()).to.be.false;
        } else {
            await uprofileUpdatePage.getPetInput().click();
            expect(await uprofileUpdatePage.getPetInput().isSelected()).to.be.true;
        }
        await uprofileUpdatePage.save();
        expect(await uprofileUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await uprofileComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });*/

    /* it('should delete last Uprofile', async () => {
        const nbButtonsBeforeDelete = await uprofileComponentsPage.countDeleteButtons();
        await uprofileComponentsPage.clickOnLastDeleteButton();

        uprofileDeleteDialog = new UprofileDeleteDialog();
        expect(await uprofileDeleteDialog.getDialogTitle())
            .to.eq('jhipsterpressApp.uprofile.delete.question');
        await uprofileDeleteDialog.clickOnConfirmButton();

        expect(await uprofileComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
