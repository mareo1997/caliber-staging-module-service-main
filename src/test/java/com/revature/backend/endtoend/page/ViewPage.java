package com.revature.backend.endtoend.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ViewPage {

	private WebDriver driver;

	// View SWOT component

	// Locators for the "+" buttons inside the four toast cards
	@FindBy(xpath = "//*[@id='swot-table']/tr/td[1]//*[@type='submit']")
	private WebElement addItemStrengthButton;

	@FindBy(xpath = "//*[@id='swot-table']/tr/td[2]//*[@type='submit']")
	private WebElement addItemWeaknessButton;

	@FindBy(xpath = "//*[@id='swot-table']/tr[2]/td[1]//*[@type='submit']")
	private WebElement addItemOpportunityButton;

	@FindBy(xpath = "//*[@id='swot-table']/tr[2]/td[2]//*[@type='submit']")
	private WebElement addItemThreatButton;

	// Buttons to find specific swots inside each of the four cards (Strength, Weakness, Opportunity, and Threat) 
	// Need a way to select a specific item if there are multiple 
	@FindBy(xpath = "//*[@id='swot-table']/tr/td[1]//*button[@class='btntoggle-btn']")
	private List<WebElement> specificItemAddItemStrength;

	@FindBy(xpath = "//*[@id='swot-table']/tr/td[2]//*button[@class='btn toggle-btn']")
	private List<WebElement> specificItemAddItemWeaknessButton;

	@FindBy(xpath = "//*[@id='swot-table']/tr[2]/td[1]//*button[@class='btn toggle-btn']")
	private List<WebElement> specificItemAddItemOpportunityButton;

	@FindBy(xpath = "//*[@id='swot-table']/tr[2]/td[2]//*button[@class='btn toggle-btn']")
	private List<WebElement> specificItemAddItemThreatButton;

	@FindBy(xpath = "//*[@id='type']")
	private WebElement updateButtonSwotItemType;

	// @FindBy(name='Enter item')
	@FindBy(xpath = "//*input[@id='name']")
	private WebElement updateButtonSwotName;

	// @FindBy(name='Enter note')
	@FindBy(xpath = "//*input[@id='note']")
	private WebElement updateButtonSwotNote;

	// Locate a toast message after an action displays it on the page
	@FindBy(xpath = "//*[@class='toast-box']")
	private List<WebElement> toastMessage;

	// Buttons available when an individual swot has been clicked
	// @FindBy(name="Update SWOT")
	@FindBy(xpath = "//*button[contains(.,'Update SWOT')]")
	private WebElement updateButtonIndividualSwot;

	// @FindBy(name = "Delete Swot Item")
	@FindBy(xpath = "//*button[contains(.,'Delete Swot Item')]")
	private WebElement deleteButtonIndividualSwot;

	// Locator for button to change the description for a swot
	// @FindBy(name="Change SWOT Description")
	@FindBy(xpath = "//*button[contains(.,'Change SWOT Description')]")
	private WebElement changeSwotDescriptionButton;

	// Update Description Textbox and Update Description Button
	@FindBy(xpath = "//*input[@id='content']")
	private WebElement updateSwotDescriptionTextbox;

	@FindBy(xpath = "//button[contains(.,'Update Description')]")
	private WebElement updateSwotDescriptionSubmitButton;

	@FindBy(xpath = "//*button[contains(.,'Delete SWOT')][@class='btntoggle-btn']")
	private WebElement deleteSwotButton;

	// Back to home page link
	@FindBy(xpath = "//*[@id='homeLink']")
	private WebElement homePageLink;
	
	@FindBy(xpath = "//*[@id='selectSwot']")
	private WebElement selectSwotTableByTime;
	
	// Delete button
	@FindBy(xpath = "//button[contains(.,'Delete')]") 
	private List<WebElement> deleteButton;
	
	public ViewPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void clickAddItemStrengthButton() {
		this.addItemStrengthButton.click();
	}
	
	public void clickAddItemWeaknessButton() {
		this.addItemWeaknessButton.click();
	}
	
	public void clickAddItemOpportunityButton() {
		this.addItemOpportunityButton.click();
	}
	
	public void clickAddItemThreatButton() {
		this.addItemThreatButton.click();
	}

	public void clickSpecificItemAddItemStrengthButton(int index) {
		this.specificItemAddItemStrength.get(index).click();
	}
	
	public void clickSpecificItemAddItemWeaknessButton(int index) {
		this.specificItemAddItemWeaknessButton.get(index).click();
	}
	
	public void clickSpecificItemAddItemOpportunityButton(int index) {
		this.specificItemAddItemOpportunityButton.get(index).click();
	}
	
	public void clickSpecificItemAddItemThreatButton(int index) {
		this.specificItemAddItemThreatButton.get(index).click();
	}
	
	public void selectUpdateButtonSwotItemType(String type) {
		type = type.toUpperCase();
		Select drpType = new Select(updateButtonSwotItemType);
		drpType.selectByValue(type);
	}
	
	public void setUpdateButtonSwotName(String text) {
		this.updateButtonSwotName.clear();
		this.updateButtonSwotName.sendKeys(text);
	}
	
	public void setUpdateButtonSwotNote(String text) {
		this.updateButtonSwotNote.clear();
		this.updateButtonSwotNote.sendKeys(text);
	}
	
	// Function to interact with toast message in some way, if needed
//	public void somethingToastMessage() {
//		
//	}
	
	public void clickUpdateButtonIndividualSwot() {
		this.updateButtonIndividualSwot.click();
	}
	
	public void clickDeleteButtonIndividualSwot() {
		this.deleteButtonIndividualSwot.click();
	}
	
	public void clickChangeSwotDescriptionButton() {
		this.changeSwotDescriptionButton.click();
	}
	
	public void setUpdateDescription(String text) {
		this.updateSwotDescriptionTextbox.clear();
		this.updateSwotDescriptionTextbox.sendKeys(text);
	}
	
	public void clickUpdateSwotDescriptionSubmitButton() {
		this.updateSwotDescriptionSubmitButton.click();
	}
	
	public void clickDeleteSwotButton() {
		this.deleteSwotButton.click();
	}

	public void clickReturnToHomepage() {
		this.homePageLink.click();
	}
	
	// selecting for this one is difficult because it doesn't have
	// an intuitive locator. This method could use a little work
	// Corresponds with id "selectSwot" on view-swot component.
	public void selectSwotTableByTime(String time) {
		Select drpTime = new Select(selectSwotTableByTime);
		drpTime.selectByVisibleText(time);
	}

	//Hold-over delete button from original pom
	public void clickDeleteItem(int id) {
		this.deleteButton.get(id).click();
	}
	
	// Return methods for the page elements
	public WebElement getAddItemStrengthButton() {
		return addItemStrengthButton;
	}
	
	public WebElement getAddItemWeaknessButton() {
		return addItemWeaknessButton;
	}
	
	public WebElement getAddItemOpportunityButton() {
		return addItemOpportunityButton;
	}
	
	public WebElement getAddItemThreatButton() {
		return addItemThreatButton;
	}
	
	public List<WebElement> getSpecificItemAddItemStrength() {
		return specificItemAddItemStrength;
	}
	
	public List<WebElement> getSpecificItemAddItemWeaknessButton() {
		return specificItemAddItemWeaknessButton;
	}
	
	public List<WebElement> getSpecificItemAddItemOpportunityButton() {
		return specificItemAddItemOpportunityButton;
	}
	
	public List<WebElement> getSpecificItemAddItemThreatButton() {
		return specificItemAddItemThreatButton;
	}
	
	public WebElement getUpdateButtonSwotItemType() {
		return updateButtonSwotItemType;
	}
	
	public WebElement getUpdateButtonSwotName() {
		return updateButtonSwotName;
	}

	public WebElement getUpdateButtonSwotNote() {
		return updateButtonSwotNote;
	}

	public List<WebElement> getToastMessage() {
		return toastMessage;
	}

	public WebElement getUpdateButtonIndividualSwot() {
		return updateButtonIndividualSwot;
	}

	public WebElement getDeleteButtonIndividualSwot() {
		return deleteButtonIndividualSwot;
	}

	public WebElement getChangeSwotDescriptionButton() {
		return changeSwotDescriptionButton;
	}

	public WebElement getUpdateSwotDescriptionTextbox() {
		return updateSwotDescriptionTextbox;
	}

	public WebElement getUpdateSwotDescriptionSubmitButton() {
		return updateSwotDescriptionSubmitButton;
	}

	public WebElement getDeleteSwotButton() {
		return deleteSwotButton;
	}

	public WebElement getHomePageLink() {
		return homePageLink;
	}
	
	public WebElement getSelectSwotTableByTime() {
		return selectSwotTableByTime;
	}
	
	public List<WebElement> getDeleteButton() {
		return deleteButton;
	}

}
